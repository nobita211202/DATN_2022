package org.datn.service.Impl;

import org.datn.dao.CoursDao;
import org.datn.dao.OrderDetailDao;
import org.datn.entity.Cours;
import org.datn.entity.OrderDetail;
import org.datn.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    CoursDao coursDao;

    @Autowired
    OrderDetailDao orderDetailDao;

    @Override
    public List<Cours> getAllCours() {
        return coursDao.findAll();
    }

    @Override
    public OrderDetail findByOrderID(Long id) {
        return orderDetailDao.findByOrderID(id);
    }
}
