package org.datn.dao;

import org.datn.entity.OrderDetail;
import org.datn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDetailDao extends JpaRepository<OrderDetail,Long> {
    @Query("SELECT e FROM OrderDetail e WHERE e.id = :id")
    OrderDetail findByOrderID(Long id);
}
