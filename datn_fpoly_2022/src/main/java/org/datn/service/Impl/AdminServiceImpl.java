package org.datn.service.Impl;

import org.datn.dao.AdminDao;
import org.datn.entity.Admin;
import org.datn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Override
    public List<Admin> getAll() {
        return adminDao.findAll();
    }

    @Override
    public Optional<Admin> findById(long id) {
        return adminDao.findById(id);
    }

    @Override
    public Admin save(Admin admin) {
        return adminDao.save(admin);
    }

    @Override
    public void delete(long id) {
         adminDao.deleteById(id);
    }
    @Override
    public Admin put(Admin admin) {
        return adminDao.save(admin);
    }

    @Override
    public boolean exists(long id){
        return adminDao.existsById(id);
    }

}
