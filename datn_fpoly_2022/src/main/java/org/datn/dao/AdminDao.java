package org.datn.dao;

import org.datn.entity.Admin;
import org.datn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AdminDao extends JpaRepository<Admin, Long> {
    @Query("SELECT a FROM Admin a WHERE a.username LIKE :username")
    User findByUsernameAdmin(String username);
}
