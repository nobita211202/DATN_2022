package org.datn.dao;

import org.datn.entity.UsersRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRoleDao extends JpaRepository<UsersRole,Long> {
}
