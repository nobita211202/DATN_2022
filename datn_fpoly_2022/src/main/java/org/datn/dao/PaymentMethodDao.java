package org.datn.dao;

import org.datn.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodDao extends JpaRepository<PaymentMethod,Long> {
}
