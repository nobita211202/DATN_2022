package org.datn.dao;

import org.datn.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDao extends JpaRepository<Order,Long> {
    @Query(value = "CALL update_status_pay_success(?1)",nativeQuery = true)
    void paymentSuccessAndUpdateStatus(String code);
}
