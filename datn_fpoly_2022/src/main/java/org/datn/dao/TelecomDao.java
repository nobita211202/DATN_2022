package org.datn.dao;

import org.datn.entity.Telecom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelecomDao extends JpaRepository<Telecom,Long> {
    Telecom findByCode(String code);
}
