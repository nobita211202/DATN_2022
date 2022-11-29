package org.datn.service.Impl;

import org.datn.dao.CoursDao;
import org.datn.dao.OrderDao;
import org.datn.entity.Cours;
import org.datn.entity.Order;
import org.datn.entity.OrderDetail;
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

    @Override
    public float getOrderPrice(List<OrderDetail> orderDetailList) {
        float totalItems=0f;
        float singleItem=0f;
        for (OrderDetail detail: orderDetailList){
            Long itemId = detail.getCourse().getId();
            Optional<Cours> cours= coursDao.findById(itemId);
            if (cours.isPresent()){
                Cours cours1 = cours.get();
                singleItem=cours1.getPrice();
                totalItems = totalItems+singleItem;
                detail.setCourse(cours1);
                detail.setPrice(singleItem);
            }

        }
        return totalItems;
    }

    @Override
    public Order createOrder(Order order) {
        return orderDao.save(order);
    }

    @Override
    public Order acceptOrder(Order order) {
        return orderDao.save(order);
    }


}
