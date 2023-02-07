package org.datn.dao;

import org.datn.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderDao extends JpaRepository<Order,Long> {
    @Query(value = "CALL update_status_pay_success(?1)",nativeQuery = true)
    void paymentSuccessAndUpdateStatus(String code);
    @Query(value = "select to_char(orders.created, 'YYYY-MM-DD') as date,sum(orders_detail.price) as doanhthu from orders\n" +
            "            join orders_detail ON orders_detail.order_id = orders.order_id\n" +
            "            WHERE orders.created between to_date(?1, 'YYYY-MM-DD') and to_date(?2, 'YYYY-MM-DD')\n" +
            "            GROUP by to_char(orders.created, 'YYYY-MM-DD')",nativeQuery = true)
    List<Map<String,String>> getByDate(String from, String dateTo);
}
