package org.datn.DAO;

import org.datn.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<Payment,Long> {
}
