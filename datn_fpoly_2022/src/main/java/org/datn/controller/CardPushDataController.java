package org.datn.controller;

import lombok.RequiredArgsConstructor;
import org.datn.bean.CardRequest;
import org.datn.dao.CardDao;
import org.datn.entity.Card;
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
    private final CardDao cardDao;

    @PostMapping("/callback")
    public ResponseEntity<?> callback(HttpServletRequest request) throws IOException {
        return cardPushDataService.callback(request);
    }

    @PostMapping("/push")
    public ResponseEntity<?> pushCard(CardRequest card) throws IOException {
        return cardPushDataService.pushCard(card);
    }
    @PutMapping("/update-status/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable String id) {
        Card card = cardDao.findByRequestId(id);
        card.setStatus(2);
        return ResponseEntity.ok(cardDao.save(card));
    }

}
