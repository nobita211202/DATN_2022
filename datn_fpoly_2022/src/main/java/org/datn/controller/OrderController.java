package org.datn.controller;

import org.datn.entity.Cours;
import org.datn.entity.Order;
import org.datn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/getAllCours")
    public ResponseEntity<List<Cours>> getAllCours(){
        List<Cours> coursList=orderService.getAllCours();
        return ResponseEntity.ok(coursList);
    }
    @GetMapping("/getOrder/{orderId}")
    public ResponseEntity<Order> getOrderDetail(@PathVariable Long orderId){
        Order order = orderService.getOrderDetail(orderId);
        return ResponseEntity.ok(order);
    }
    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order){
//        Float amount = orderService.getCartAmount();
        return null;
    }
}
