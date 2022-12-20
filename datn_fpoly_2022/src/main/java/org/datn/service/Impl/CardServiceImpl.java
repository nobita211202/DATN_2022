package org.datn.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.datn.dao.CardDao;
import org.datn.entity.Card;
import org.datn.service.Services;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackOn = RuntimeException.class)
@Service
public class CardServiceImpl implements Services<Card> {
    private final CardDao cardDao;
    @Override
    public ResponseEntity<?> save(Card entity) {
        log.info("Save card: {}", entity);
        return ResponseEntity.ok(cardDao.save(entity));
    }

    @Override
    public ResponseEntity<?> update(Card entity) {
        Card card2 = cardDao.findByCode(entity.getCode());
        if (card2 == null) {
            log.info("Card not found");
            return ResponseEntity.badRequest().body("Card not found");
        }
        card2.setStatus(entity.getStatus());
        return ResponseEntity.ok(cardDao.save(card2));
    }

    @Override
    public ResponseEntity<?> delete(Card entity) {
        log.info("Delete card: {}", entity);
        cardDao.delete(entity);
        return ResponseEntity.ok("Delete card success");
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
       try {
              cardDao.deleteById(id);
              return ResponseEntity.ok("Delete card success");
         } catch (Exception e) {
              log.error("Delete card error: {}", e.getMessage());
              return ResponseEntity.badRequest().body("Delete card error");
       }
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        return ResponseEntity.ok(cardDao.findById(id).orElseThrow(() -> new RuntimeException("Card not found")));
    }

    @Override
    public ResponseEntity<Collection<?>> findAll() {
        return ResponseEntity.ok(cardDao.findAll());
    }
    public Card findByTransactionIdAndStatus(String transactionId, Integer status) {
        return cardDao.findByTransactionIdAndStatus(transactionId, status);
    }
}
