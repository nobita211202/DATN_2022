package org.datn.dao;

import org.datn.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
public interface CardDao extends JpaRepository<Card,Long> {
    Card findByCode(String code);
    @Query(value = "select * from card where request_code = ?1 and status = ?2",nativeQuery = true)
    Card findByTransactionIdAndStatus(String transactionId, Integer status);
    @Query(value = "select * from card where code = ?1 OR seri = ?2",nativeQuery = true)
    Card findByCodeOrSerial(String code, String serial);
    @Query(value = "select * from card where user_id = ?1",nativeQuery = true)
    Collection<Card> getAllHistoryByUserId(Long userId);

    @Query(value = "select * from card where request_code = ?1",nativeQuery = true)
    Card findByRequestId(String requestCode);
}
