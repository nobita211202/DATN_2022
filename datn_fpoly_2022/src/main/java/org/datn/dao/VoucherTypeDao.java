package org.datn.DAO;

import org.datn.entity.VoucherType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherTypeDao extends JpaRepository<VoucherType,Long> {
}
