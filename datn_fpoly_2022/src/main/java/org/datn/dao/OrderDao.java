package org.datn.DAO;

import org.datn.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Long> {
}
