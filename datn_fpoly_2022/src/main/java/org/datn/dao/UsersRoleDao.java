package org.datn.dao;

import org.datn.entity.User;
import org.datn.entity.UsersRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRoleDao extends JpaRepository<UsersRole,Long> {
    @Query("select u from User u join UsersRole ur on u.id = ur.user where ur.role.id =:id ")
    List<User> getUserByIdRole(@Param("id") long idRole);
}
