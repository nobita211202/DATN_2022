package org.datn.service.Impl;

import org.datn.dao.VoucherDao;
import org.datn.entity.Voucher;
import org.datn.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    VoucherDao voucherDao;
    @Override
    public List<Voucher> findAll() {
        return voucherDao.findAll();
    }

    @Override
    public Voucher createVoucher(Voucher voucher) {
        return voucherDao.save(voucher);
    }

    @Override
    public void deleteVoucher(Long id) {
        voucherDao.deleteById(id);
    }

    @Override
    public Voucher updateVoucher(Voucher voucher) {
        return voucherDao.save(voucher);
    }

    @Override
    public Voucher findByIDVoucher(Long id) {
        return voucherDao.findById(id).get();
    }
}
