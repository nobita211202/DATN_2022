package org.datn.service.Impl;

import org.datn.dao.CoursDao;
import org.datn.entity.Cours;
import org.datn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    CoursDao coursDao;
    @Override
    public List<Cours> getAllCours() {
        return coursDao.findAll();
    }
}
