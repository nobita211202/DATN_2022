package org.datn.service.Impl;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.RequestBody;
import org.datn.entity.Card;
import org.datn.utils.HashAlgorithm;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Random;

@Service
@Slf4j
public class CardPushDataService{
    public ResponseEntity<?> callback(){
        return ResponseEntity.ok("OK");
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
         return ResponseEntity.ok(response.body().string());
        }
    }
}
