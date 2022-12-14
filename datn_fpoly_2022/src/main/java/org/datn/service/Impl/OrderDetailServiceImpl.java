package org.datn.service.Impl;

import org.datn.bean.History;
import org.datn.dao.OrderDetailDao;
import org.datn.entity.OrderDetail;
import org.datn.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailDao orderDetailDao;
    @Override
    public void orderByUserIdAndMultiCourse(Long userId, Long[] multiCourse) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://18.143.34.109/datn_2022?sslmode=require", "dbmasteruser", "lavantho0508");
        PreparedStatement preparedStatement = connection.prepareStatement("CALL user_order(?,?)");
        preparedStatement.setLong(1,userId);
        Array array = connection.createArrayOf("bigint",multiCourse);
        preparedStatement.setArray(2,array);
        preparedStatement.execute();
    }
    @Override
    public List<History> findByUserId(Long userId){
        return orderDetailDao.findByUserId(userId);
    }

    @Override
    public ResponseEntity<?> deleteOrderDetail(Long id) {
        try {
            orderDetailDao.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
