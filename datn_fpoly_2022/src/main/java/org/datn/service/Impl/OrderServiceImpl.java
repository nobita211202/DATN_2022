package org.datn.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.datn.dao.OrderDao;
import org.datn.entity.Order;
import org.datn.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    @Override
    public ResponseEntity<?> getAllOrder() {
        log.info("Get all order");
        return ResponseEntity.ok(orderDao.findAll());
    }

    @Override
    public ResponseEntity<?> getOrderById(Long id) {
        log.info("Get order id {} ",id);
        Order order = orderDao.findById(id).orElseThrow(()-> new RuntimeException("Order not found"));
        return ResponseEntity.ok(order);
    }

    @Override
    public ResponseEntity<?> paymentSuccessAndUpdateStatus(String code) {
        try {
            log.info("Payment success and update status order code {} ",code);
            orderDao.paymentSuccessAndUpdateStatus(code);
            return ResponseEntity.ok(Map.of("message","Payment success","status", HttpStatus.OK.value(),"order_code",code));
        }catch (Exception e){
            log.error("Payment fail");
            return ResponseEntity.ok(Map.of("message","Payment fail","status", HttpStatus.BAD_REQUEST.value(),"order_code",code));
        }
    }
}
