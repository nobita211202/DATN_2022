package org.datn.controller;

import org.datn.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/order-detail")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;
    @PostMapping("/order/{userId}")
    public void orderByUserIdAndMultiCourse(@PathVariable Long userId,@RequestBody Long[] multiCourse) throws SQLException, ClassNotFoundException {
        orderDetailService.orderByUserIdAndMultiCourse(userId,multiCourse);
    }
}
