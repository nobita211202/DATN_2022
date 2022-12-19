package org.datn.controller;

import lombok.RequiredArgsConstructor;
import okhttp3.*;
import okhttp3.RequestBody;
import org.datn.service.Impl.CardPushDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.Serializable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auto-card")
@SuppressWarnings("unused")
public class CardPushDataController implements Serializable{

    private static final long serialVersionUID = 1L;
    private final CardPushDataService cardPushDataService;
    @GetMapping("/callback")
    @ResponseBody
    public String pushData(){
        return "";
    }
    public ResponseEntity<String> pushCard() throws IOException {
        return cardPushDataService.pushCard();
    }
}
