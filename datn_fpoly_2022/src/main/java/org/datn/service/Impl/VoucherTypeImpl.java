package org.datn.service.Impl;

import org.datn.dao.VoucherTypeDao;
import org.datn.entity.VoucherType;
import org.datn.service.VoucherTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Service
public class VoucherTypeImpl implements VoucherTypeService {
    @Autowired
    VoucherTypeDao voucherTypeDao;
    @Override
    public List<VoucherType> findAll() {
        return voucherTypeDao.findAll();
    }

    @Override
    public VoucherType createVoucherType(VoucherType voucherType) {
        return voucherTypeDao.save(voucherType);
    }

    @Override
    public void deleteVoucherType(Long id) {
        voucherTypeDao.deleteById(id);
    }

    @Override
    public VoucherType updateVoucherType(VoucherType voucherType) {
        return voucherTypeDao.save(voucherType);
    }

    @Override
    public VoucherType findByIDVoucherType( Long id) {
        return voucherTypeDao.findById(id).get();
    }
}
