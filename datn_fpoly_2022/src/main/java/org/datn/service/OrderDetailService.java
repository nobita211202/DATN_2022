package org.datn.service;

import org.datn.bean.History;
import org.datn.entity.OrderDetail;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface OrderDetailService {
    void orderByUserIdAndMultiCourse(Long userId, Long[] multiCourse) throws SQLException, ClassNotFoundException;

    List<History> findByUserId(Long userId);

    boolean existsCourseInOrderDetail(Long userId,Long courseId);
    ResponseEntity<?> deleteOrderDetail(Long id);
    Map coursePayment(Long userId, Long[] multiCourse) throws ClassNotFoundException, SQLException;
}
