package org.datn.service;

import org.datn.entity.VoucherType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoucherTypeService {

    List<VoucherType> findAll();

    VoucherType createVoucherType(VoucherType voucherType);

    void deleteVoucherType(Long id);

    VoucherType updateVoucherType(VoucherType voucherType);

    VoucherType findByIDVoucherType(Long id);
}
