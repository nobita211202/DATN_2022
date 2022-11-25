package org.datn.service;

import org.datn.entity.Voucher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoucherService {
    List<Voucher> findAll();


    Voucher createVoucher(Voucher voucher);

    void deleteVoucher(Long id);
    Voucher updateVoucher(Voucher voucher);

    Voucher findByIDVoucher(Long id);
}
