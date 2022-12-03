package org.datn.controller;

import org.datn.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/order-detail")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;
    @PostMapping("/order/{userId}")
    public Map orderByUserIdAndMultiCourse(@PathVariable Long userId, @RequestBody Long[] multiCourse) throws SQLException, ClassNotFoundException {
       try {
              orderDetailService.orderByUserIdAndMultiCourse(userId,multiCourse);
              return Map.of("message","success","status",200);
       }catch (SQLException e){
           return Map.of("message",e.getMessage(),"status",500);
       }
    }
}
