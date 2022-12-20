package org.datn.dao;

import org.datn.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardDao extends JpaRepository<Card,Long> {
    Card findByCode(String code);
    @Query(value = "select * from card where trans_code = ?1 and status = ?2",nativeQuery = true)
    Card findByTransactionIdAndStatus(String transactionId, Integer status);
}
