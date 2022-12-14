package org.datn.controller;

import lombok.RequiredArgsConstructor;
import org.datn.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/get")
    public ResponseEntity<?> getAllOrder(){
        return orderService.getAllOrder();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }
    @PostMapping("/payment/{code}")
    public ResponseEntity<?> paymentSuccessAndUpdateStatus(@PathVariable String code){
        return orderService.paymentSuccessAndUpdateStatus(code);
    }

}
