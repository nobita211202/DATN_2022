package org.datn.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.RequestBody;
import org.datn.entity.Card;
import org.datn.utils.HashAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackOn = RuntimeException.class)
public class CardPushDataService{
    private final CardServiceImpl cardService;
    public ResponseEntity<?> callback(HttpServletRequest request) throws IOException {
        @Data
        class RequestData{
            private Integer tran_id;
            private String sign,type,code,serial;
            private Float declare,amount,money,balance;
            private String message;
        }
        Scanner sc = new Scanner(request.getInputStream());
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext())
            sb.append(sc.nextLine());
        log.info("Data: {}", sb);
        ObjectMapper objectMapper = new ObjectMapper();
        RequestData dataRequest = objectMapper.readValue(sb.toString(), RequestData.class);
        Card card = cardService.findByTransactionIdAndStatus(String.valueOf(dataRequest.getTran_id()), 1);
        if (card == null) {
            log.error("Card not found");
            return ResponseEntity.ok("Card not found");
        }
        Float money = dataRequest.getMoney();

        if(money!= null && dataRequest.getMoney() > 0){
           card.getUser().setMoney(card.getUser().getMoney() + money);
           log.info("Money: {}", money);
           log.info("charge money success with username: {}", card.getUser().getUsername());
        }
        card.setStatus(1);
        cardService.save(card);
        assert money != null;
        return ResponseEntity.ok(Map.of("status", HttpStatus.OK.value(), "message", "Successfully", "money", money));
    }
    public ResponseEntity<?> pushCard(Card card) throws IOException {
        String url = "https://napthe247.vn/api/charging/send_card.html" ;
        String partnerId = "850810EB-83F2-4BDA-B4FB-2EE46FB0AF65" ;
        String type = card.getCardPrice().getTelecom().getCode();
        String code = card.getCode();
        String serial = card.getSeri();
        String amount = card.getCardPrice().getPrice().toString();
        String partnerKey = "937799416055" ;
        String transId = String.valueOf(new Random().nextInt(100000000));
        String signature = HashAlgorithm.MD5(partnerKey + transId);
        log.info("signature {} encoded md5  : " + signature);
        String callbackUrl="http://fpolycourse.xyz:8888/api/auto-card/callback";
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("type",type)
                .addFormDataPart("code",code)
                .addFormDataPart("serial",serial)
                .addFormDataPart("amount",amount)
                .addFormDataPart("tran_id",transId)
                .addFormDataPart("partner_id",partnerId)
                .addFormDataPart("sign",signature == null ? "" : signature)
                .addFormDataPart("callback",callbackUrl)
                .build();
        Request request = new Request.Builder().
                url(url).
                method("POST", body).
                addHeader("Content-Type", MediaType.ALL_VALUE).
                build();
     try (Response response = client.newCall(request).execute()) {
         assert response.body() != null;
         log.info("Response: {}", response.body().string() == null ? "null" : response.body().string());
         card.setTransCode(transId);
         cardService.save(card);
         log.info("Save card serial : {} success", card.getSeri());
         return ResponseEntity.ok(response.body().string());
        }
    }
}
