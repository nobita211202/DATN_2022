package org.datn.dao;

import org.datn.entity.LoginFail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginFailDao extends JpaRepository<LoginFail,Long> {
}
