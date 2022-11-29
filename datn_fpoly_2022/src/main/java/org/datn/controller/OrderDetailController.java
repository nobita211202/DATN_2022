package org.datn.controller;

import org.datn.DTO.OrderDTO;
import org.datn.DTO.ResponseOrderDTO;
import org.datn.bean.ResponseData;
import org.datn.entity.Cours;
import org.datn.entity.Order;
import org.datn.entity.OrderDetail;
import org.datn.entity.User;
import org.datn.service.CoursService;
import org.datn.service.OrderDetailService;
import org.datn.service.OrderService;
import org.datn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@CrossOrigin("*")
@RestController("/api/orderdetail")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    OrderService orderService;
    @Autowired
    CoursService coursService;
    @Autowired
    UserService userService;

    private Logger logger = LoggerFactory.getLogger(OrderDetailController.class);

    @GetMapping("/getAllCours")
    public ResponseEntity<List<Cours>> getAllCours(){
        List<Cours> coursList= coursService.findAll();
        return ResponseEntity.ok(coursList);
    }
    @GetMapping("/getOrder/{orderId}")
    public ResponseEntity<OrderDetail> getOrderDetail(@PathVariable Long orderId){      //lấy ra Hoa đơn chi tiết
        OrderDetail orderDetail=orderDetailService.findByOrderID(orderId);
        return ResponseEntity.ok(orderDetail);
    }
    @PostMapping("/placeOrder")
    public ResponseEntity<OrderDetail> placeOrder( @RequestBody List<OrderDetail> orderDetailList){
        float price = orderService.getOrderPrice(orderDetailList);

        User user = userService.findByEmail(orderDetailList.get(1).getOrder().getUser().getEmail());

        Order order = new Order();
        order.setCode(randomCode());
        order.setUser(user);
        order.setEffectFrom(Instant.now());
        order.setEffectUntil(Instant.now());
        order.setPrice(price);
        order.setStatus((short) 0);
        orderService.createOrder(order);

        for (OrderDetail orderDetail :orderDetailList
             ) {
            OrderDetail orderDetail1 = new OrderDetail();
            orderDetail1.setOrder(order);
            orderDetail1.setCourse(orderDetail.getCourse());
            orderDetail1.setStatus(0);
            orderDetail1.setPrice(orderDetail.getPrice());
            orderDetail1.setCreated(Date.from(Instant.now()));

            return  ResponseEntity.ok(orderDetail1);
        }
        return null;
    }
    @PutMapping("/acceptOrder/{id}")
    public ResponseEntity<Order> acceptOrder(@PathVariable("id") Long id,
                        @RequestBody Order order){
        order.setStatus((short) 2);
        order.setEffectUntil(Instant.now());
        orderService.acceptOrder(order);
        return ResponseEntity.ok(order);
    }

    public String randomCode (){
        SecureRandom random = new SecureRandom();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();
        byte[] randomBytes = new byte[24];
        random.nextBytes(randomBytes);
        System.out.println(base64Encoder.encodeToString(randomBytes));
        return base64Encoder.encodeToString(randomBytes);
    }
}
