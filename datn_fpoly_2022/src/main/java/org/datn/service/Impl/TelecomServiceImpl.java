package org.datn.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.datn.dao.TelecomDao;
import org.datn.entity.Telecom;
import org.datn.service.Services;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
@Service
@Slf4j
@Transactional(rollbackOn = RuntimeException.class)
@RequiredArgsConstructor
public class TelecomServiceImpl implements Services<Telecom> {
    private final TelecomDao telecomDao;
    @Override
    public ResponseEntity<?> save(Telecom entity) {
        log.info("Save telecom: {}", entity);
        return ResponseEntity.ok(telecomDao.save(entity));
    }

    @Override
    public ResponseEntity<?> update(Telecom entity) {
        Telecom telecom2 = telecomDao.findByCode(entity.getCode());
        if (telecom2 == null) {
            log.info("Telecom not found");
            return ResponseEntity.badRequest().body("Telecom not found");
        }
        telecom2.setCode(entity.getCode());
        telecom2.setName(entity.getName());
        log.info("Update telecom: {}", entity);
        return ResponseEntity.ok(telecomDao.save(telecom2));
    }

    @Override
    public ResponseEntity<?> delete(Telecom entity) {
        log.info("Delete telecom: {}", entity);
        telecomDao.delete(entity);
        return ResponseEntity.ok("Delete telecom success");
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        try {
            telecomDao.deleteById(id);
            return ResponseEntity.ok("Delete telecom success");
        } catch (Exception e) {
            log.error("Delete telecom error: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Delete telecom error");
        }
    }

    @Override
    public Telecom findById(Long id) {
        return telecomDao.findById(id).orElseThrow(() -> new RuntimeException("Telecom not found"));
    }

    @Override
    public ResponseEntity<Collection<?>> findAll() {
        return ResponseEntity.ok(telecomDao.findAll());
    }
}
