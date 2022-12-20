package org.datn.dao;

import org.datn.entity.CardPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardPriceDao extends JpaRepository<CardPrice,Long> {
}
