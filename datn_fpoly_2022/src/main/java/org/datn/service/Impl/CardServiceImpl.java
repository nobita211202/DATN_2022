package org.datn.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.datn.dao.CardDao;
import org.datn.entity.Card;
import org.datn.service.Services;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackOn = RuntimeException.class)
@Service
public class CardServiceImpl implements Services<Card> {
    private final CardDao cardDao;
    @Override
    public ResponseEntity<?> save(Card entity) {
       try {
           if(isExistCard(entity.getCode(),entity.getSeri())){
               log.error("the user : {} used a card {} that exists in the system",entity.getUser().getUsername(),entity.getCode());
               return ResponseEntity.ok(Map.of("message","Card is exist","status", HttpStatus.BAD_REQUEST.value()));
           }
           else if(entity.getCode().matches("^[0-9]{11,17}+$") && entity.getSeri().matches("^[0-9]{11,17}+$")) {
               log.info("Save card type: {} and serial {} and code {}", entity.getCardPrice().getTelecom(),entity.getSeri(),entity.getCode());
               return ResponseEntity.ok(Map.of("message","Save card success","status", HttpStatus.OK.value(),"data", cardDao.save(entity)));
           }else
               return ResponseEntity.ok(Map.of("message","Code or serial is not number","status", HttpStatus.BAD_REQUEST.value()));
       } catch (Exception e) {
           log.error("Save card error: {}", e.getMessage());
           e.printStackTrace();
           return ResponseEntity.badRequest().body("Save card error");
       }
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
        return ResponseEntity.ok(Map.of("message","Delete card success","status", HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
       try {
              cardDao.deleteById(id);
              return ResponseEntity.ok(Map.of("message","Delete card success","status", HttpStatus.OK.value()));
         } catch (Exception e) {
              log.error("Delete card error: {}", e.getMessage());
              return ResponseEntity.badRequest().body("Delete card error");
       }
    }

    @Override
    public Card findById(Long id) {
        return cardDao.findById(id).orElseThrow(() -> new RuntimeException("Card not found"));
    }

    @Override
    public ResponseEntity<Collection<?>> findAll() {
        return ResponseEntity.ok(cardDao.findAll());
    }
    public Card findByRequestIdAndStatus(String transactionId, Integer status) {
        return cardDao.findByTransactionIdAndStatus(transactionId, status);
    }
    public boolean isExistCard(String code, String serial) {
        return cardDao.findByCodeOrSerial(code, serial) != null;
    }
}
