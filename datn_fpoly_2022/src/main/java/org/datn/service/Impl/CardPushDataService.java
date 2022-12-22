package org.datn.service.Impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.RequestBody;
import org.datn.bean.CardRequest;
import org.datn.entity.Card;
import org.datn.entity.CardPrice;
import org.datn.service.Services;
import org.datn.service.UserService;
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
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor@AllArgsConstructor
class RequestData{
    private Integer tran_id,request_id,status;
    private String callback_sign,telco,code,serial;
    private Float declared_value,amount,value;
    private String message;
}

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackOn = RuntimeException.class)

public class CardPushDataService{
    private final CardServiceImpl cardService;
    private final Services<CardPrice> cardPriceService;
    private final UserService userService;
    public ResponseEntity<?> callback(HttpServletRequest request) throws IOException {
        log.info("callback had called");
        Scanner sc = new Scanner(request.getInputStream());
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext())
            sb.append(sc.nextLine());
        log.info("Data: {}", sb);
        ObjectMapper objectMapper = new ObjectMapper();
        RequestData dataRequest = objectMapper.readValue(sb.toString(), RequestData.class);
        Card card = cardService.findByRequestIdAndStatus(String.valueOf(dataRequest.getRequest_id()), 1);
        log.info("data callback card is : {}", card.toString());
        if (card == null) {
            log.error("Card not found");
            return ResponseEntity.ok("Card not found");
        }
        Float money = dataRequest.getValue();
        if(money!= null && dataRequest.getValue() > 0){
           card.getUser().setMoney(card.getUser().getMoney() + money);
           log.info("Money: {}", money);
           log.info("charge money success with username: {}", card.getUser().getUsername());
           card.setStatus(0);
           cardService.save(card);
           assert money != null;
           return ResponseEntity.ok(Map.of("status", HttpStatus.OK.value(), "message", "Successfully", "money", money));
        }
       return ResponseEntity.ok(Map.of("status",HttpStatus.BAD_REQUEST.value(),"message","Fail"));
    }
    public ResponseEntity<?> pushCard(CardRequest card) throws IOException {
        String url = "https://thecaosieure.com/chargingws/v2" ;
        CardPrice cardPrice = cardPriceService.findById(card.getCardPriceId());
        String partnerId = "73485247815" ;
        String type = cardPrice.getTelecom().getCode();
        String code = card.getCode();
        String serial = card.getSeri();
        String amount = cardPrice.getPrice()+"";
        String partnerKey = "b80e9083425d4be6d7496d38dc80932b" ;
        String requestId = String.valueOf(new Random().nextInt(100000000)+new Random().nextInt(1000000));
        String signature = HashAlgorithm.MD5(partnerKey + code + serial);
        log.info("signature {} encoded md5  : " + signature);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("telco",type)
                .addFormDataPart("code",code)
                .addFormDataPart("serial",serial)
                .addFormDataPart("amount",amount)
                .addFormDataPart("request_id",requestId)
                .addFormDataPart("partner_id",partnerId)
                .addFormDataPart("sign",signature == null ? "" : signature)
                .addFormDataPart("command","charging")
                .build();
        Request request = new Request.Builder().
                url(url).
                method("POST", body).
                addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE).
                build();
     try {
         Response response = client.newCall(request).execute();
         Card card1 = new Card();
         card1.setCardPrice(cardPrice);
         card1.setSeri(serial);
         card1.setCode(code);
         card1.setRequestCode(requestId);
         card1.setUser(userService.findById(card.getUserId()).orElseThrow(()-> new RuntimeException("User not found")));
         card1.setStatus(1);
         cardService.save(card1);
         log.info("Save card serial : {} success", card.getSeri());
         return ResponseEntity.ok(response.body().string());
     }catch (Exception e){
         log.error("Error: {}", e.getMessage());
         e.printStackTrace();
         return ResponseEntity.ok(e.getMessage());
     }
    }
}
