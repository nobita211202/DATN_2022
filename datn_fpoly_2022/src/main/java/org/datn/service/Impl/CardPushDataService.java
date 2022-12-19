package org.datn.service.Impl;

import okhttp3.*;
import okhttp3.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.Serializable;

@Service
@SuppressWarnings("unused")
public class CardPushDataService implements Serializable{

    private static final long serialVersionUID = 1L;
    public String pushData(){
        return "";
    }
    public ResponseEntity<String> pushCard() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("partner_id","850810EB-83F2-4BDA-B4FB-2EE46FB0AF65")
                .addFormDataPart("type","VMS")
                .addFormDataPart("code","452049865547")
                .addFormDataPart("serial","092051002090592")
                .addFormDataPart("amount","20000")
                .addFormDataPart("tran_id","1626118159334")
                .addFormDataPart("callback","")
                .addFormDataPart("sign","76b1406f3ea800c6b0409e1425411e8b")
                .build();
        Request request = new Request.Builder()
                .url("https://napthe247.vn/api/charging/send_card.html")
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
        assert response.body() != null;
        return ResponseEntity.ok(response.body().string());
    }
}
