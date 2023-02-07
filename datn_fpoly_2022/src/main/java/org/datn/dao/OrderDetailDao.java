package org.datn.dao;

import org.datn.bean.History;
import org.datn.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.reflect.Array;
import java.util.List;

public interface OrderDetailDao extends JpaRepository<OrderDetail, Long> {
    @Query(value = "CALL user_order(?1,ARRAY[?2])",nativeQuery = true)
    void orderByUserIdAndMultiCourse(long userId, Long[] multiCourse);
    @Query(value = " SELECT * FROM view_order_detail_by_userid(?)",nativeQuery = true)
    List<History> findByUserId(Long userId);
    @Query(value = "CALL delete_orderdetail_from_order(?1)",nativeQuery = true)
    void deleteById(Long id);

    @Query("select od from OrderDetail od join Order o on o.id = od.order.id where " +
            " od.course.id=:idCourse and o.user.id =:idUser")
    List<OrderDetail> existsByCourseAndUser(@Param("idCourse")Long idCouese,@Param("idUser")Long idUser);
}
