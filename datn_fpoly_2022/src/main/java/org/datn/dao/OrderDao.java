package org.datn.dao;

import org.datn.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query(value = "select sum(price) from orders_detail  ",nativeQuery = true)
    Integer getSumMoneyAll();
    @Query(value = "select sum(price) from orders_detail  " +
            "where orders_detail.created between to_date(?1 , 'YYYY-MM-DD')  and to_date(?2, 'YYYY-MM-DD')",nativeQuery = true)
    Integer getSumMoneyByDate(String from, String to);

    @Query(value = "\n" +
            "select count(users_role.user_id) from users \n" +
            "join users_role on users.user_id = users_role.user_id\n" +
            "where users_role.role_id = 5",nativeQuery = true)
    Integer getCountUserAll();
    @Query(value = "select count(users.user_id) from users \n" +
            "            join users_role on users.user_id = users_role.user_id \n" +
            "            where users_role.role_id = 5 and\n" +
            "            (users.created between to_date(?1 , 'YYYY-MM-DD') \n" +
            "\t\t\t and to_date(?2, 'YYYY-MM-DD')) \n" +
            "\t\t\t and users.status = 0",nativeQuery = true)
    Integer getCountUserByDate( String from, String to);


    @Query(value = "select count(comment_id) from comment_ ",nativeQuery = true)
    Integer getCountCmonmentAll();

    @Query(value = "select count(comment_id) from comment_ where " +
            "comment_.created between to_date(?1, 'YYYY-MM-DD')  and to_date(?2, 'YYYY-MM-DD')",nativeQuery = true)
    Integer getCountCmonmentByDate(String from, String to);
}
