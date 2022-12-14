package org.datn.service;

import org.datn.bean.History;
import org.datn.entity.OrderDetail;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailService {
    void orderByUserIdAndMultiCourse(Long userId, Long[] multiCourse) throws SQLException, ClassNotFoundException;
    List<History> findByUserId(Long userId);
    ResponseEntity<?> deleteOrderDetail(Long id);
}
