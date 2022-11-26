package org.datn.service.Impl;

import org.datn.dao.CoursDao;
import org.datn.dao.OrderDao;
import org.datn.entity.Cours;
import org.datn.entity.Order;
import org.datn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    CoursDao coursDao;
    @Autowired
    OrderDao orderDao;
    @Override
    public List<Cours> getAllCours() {
        return coursDao.findAll();
    }

    @Override
    public Order getOrderDetail(Long id) {
        Optional<Order> order=this.orderDao.findById(id);
        return order.isPresent() ? order.get() : null;
    }


}
