package org.datn.service;

import org.datn.entity.Cours;
import org.datn.entity.Order;
import org.datn.entity.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderService {
    List<Cours> getAllCours();

    Order getOrderDetail(Long id);

    float getOrderPrice(List<OrderDetail> orderDetailList);

    Order createOrder(Order order);

    Order acceptOrder(Order order);
}
