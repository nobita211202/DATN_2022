package org.datn.controller;

import lombok.RequiredArgsConstructor;
import org.datn.bean.CardRequest;
import org.datn.service.Impl.CardPushDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auto-card")
@SuppressWarnings("unused")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CardPushDataController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final CardPushDataService cardPushDataService;

    @PostMapping("/callback")
    public ResponseEntity<?> callback(HttpServletRequest request) throws IOException {
        return cardPushDataService.callback(request);
    }

    @PostMapping("/push")
    public ResponseEntity<?> pushCard(CardRequest card) throws IOException {
        return cardPushDataService.pushCard(card);
    }
}
