package org.datn.dao;

import org.datn.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherDao extends JpaRepository<Voucher,Long> {
}
