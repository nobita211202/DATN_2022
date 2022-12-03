package org.datn.dao;

import org.datn.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.Array;

public interface OrderDetailDao extends JpaRepository<OrderDetail, Long> {
    @Query(value = "CALL user_order(?1,ARRAY[?2])",nativeQuery = true)
    void orderByUserIdAndMultiCourse(long userId, Array multiCourse);

}
