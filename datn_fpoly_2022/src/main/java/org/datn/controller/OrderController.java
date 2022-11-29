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



}
