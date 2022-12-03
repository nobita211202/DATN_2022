package org.datn.service.Impl;

import org.datn.dao.OrderDetailDao;
import org.datn.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailDao orderDetailDao;
    @Override
    public void orderByUserIdAndMultiCourse(Long userId, Array multiCourse) {
        orderDetailDao.orderByUserIdAndMultiCourse(userId,multiCourse);
    }

}
