package org.datn.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.datn.dao.CardPriceDao;
import org.datn.entity.CardPrice;
import org.datn.service.Services;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collection;
@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(rollbackOn = RuntimeException.class)
public class CardPriceServiceImpl implements Services<CardPrice> {
    private final CardPriceDao cardPriceDao;
    @Override
    public ResponseEntity<?> save(CardPrice entity) {
        log.info("Save card price: {}", entity);
        return ResponseEntity.ok(cardPriceDao.save(entity));
    }

    @Override
    public ResponseEntity<?> update(CardPrice entity) {
        CardPrice cardPrice2 = cardPriceDao.findById(entity.getId()).orElseThrow(() -> new RuntimeException("Card price not found"));
        cardPrice2.setPrice(entity.getPrice());
        log.info("Update card price: {}", cardPrice2);
        return ResponseEntity.ok(cardPriceDao.save(cardPrice2));
    }

    @Override
    public ResponseEntity<?> delete(CardPrice entity) {
        log.info("Delete card price: {}", entity);
        cardPriceDao.delete(entity);
        return ResponseEntity.ok("Delete card price success");
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        try {
            cardPriceDao.deleteById(id);
            log.info("Delete card price id {} success",id);
            return ResponseEntity.ok("Delete card price success");
        } catch (Exception e) {
            log.error("Delete card price error: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Delete card price error");
        }
    }

    @Override
    public CardPrice findById(Long id) {
        log.info("Find card price by id: {}", id);
        return cardPriceDao.findById(id).orElseThrow(() -> new RuntimeException("Card price not found"));
    }

    @Override
    public ResponseEntity<Collection<?>> findAll() {
        log.info("Find all card price");
        return ResponseEntity.ok(cardPriceDao.findAll());
    }
    public Collection<CardPrice> findAllByTelecom(Long telecomId){
        log.info("Find all card price by telecom: {}", telecomId);
        return cardPriceDao.findByCardType(telecomId);
    }
}
