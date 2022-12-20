package org.datn.dao;

import org.datn.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDao extends JpaRepository<Card,Long> {
    Card findByCode(String code);
}
