package org.datn.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.datn.entity.Card;
import org.datn.service.Impl.CardPushDataService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auto-card")
@Slf4j
@SuppressWarnings("unused")
public class CardPushDataController implements Serializable{

    private static final long serialVersionUID = 1L;
    private final CardPushDataService cardPushDataService;

    @PostMapping("/callback")
    public ResponseEntity<?> callback(HttpServletRequest s) throws IOException {
        Scanner sc = new Scanner(s.getInputStream());
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext())
            sb.append(sc.nextLine());
        log.info("Data: {}", sb.toString());
        return cardPushDataService.callback();
    }
    @PostMapping(value = "/push")
    public ResponseEntity<?> pushCard(@RequestBody Card card) throws IOException {
        return cardPushDataService.pushCard(card);
    }
}
