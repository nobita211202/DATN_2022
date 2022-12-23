package org.datn.controller;

import lombok.RequiredArgsConstructor;
import org.datn.dao.CardDao;
import org.datn.entity.Card;
import org.datn.entity.CardPrice;
import org.datn.entity.Telecom;
import org.datn.service.Impl.CardPriceServiceImpl;
import org.datn.service.Services;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@SuppressWarnings("unused")
@RequiredArgsConstructor
@RequestMapping("/api/card-price")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CardPriceController {
    private final CardPriceServiceImpl cardPriceService;
    private final Services<Telecom> telecomService;
    private final CardDao cardDao;
    @PostMapping("/save")
    public ResponseEntity<?> save (@RequestBody CardPrice entity) {
        return cardPriceService.save(entity);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update (@RequestBody CardPrice entity) {
        return cardPriceService.update(entity);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete (@RequestBody CardPrice entity) {
        return cardPriceService.delete(entity);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById (@PathVariable Long id) {
        return cardPriceService.deleteById(id);
    }
    @GetMapping("/find/{id}")
    public CardPrice findById (@PathVariable Long id) {
        return cardPriceService.findById(id);
    }
    @GetMapping("/find-all")
    public ResponseEntity<?> findAll () {
        return cardPriceService.findAll();
    }
    @GetMapping("/find-by-telecom/{id}")
    public Collection<CardPrice> findAllByTelecom(@PathVariable Long id) {
        return cardPriceService.findAllByTelecom(id);
    }
    @GetMapping("/get-all-telecom")
    public ResponseEntity<?> getAllTelecom() {
        return telecomService.findAll();
    }
    @GetMapping("/get-history/{id}")
    public Collection<Card> getHistory(@PathVariable Long id) {
        return cardDao.getAllHistoryByUserId(id);
    }
 }
