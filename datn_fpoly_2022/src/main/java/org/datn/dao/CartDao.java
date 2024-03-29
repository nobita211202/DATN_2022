package org.datn.dao;

import org.datn.entity.Cart;
import org.datn.entity.Course;
import org.datn.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CartDao extends JpaRepository<Cart, Long> {
    boolean existsByCourseAndUser(Course course, User user);
    List<Cart> getCartByUser(User user);
}
