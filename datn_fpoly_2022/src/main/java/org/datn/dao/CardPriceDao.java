package org.datn.dao;

import org.datn.entity.CardPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface CardPriceDao extends JpaRepository<CardPrice,Long> {
    @Query(value = "select * from card_price where telecom_id = ?1",nativeQuery = true)
    Collection<CardPrice> findByCardType(Long telecomId);
}
