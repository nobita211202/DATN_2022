package org.datn.DAO;

import org.datn.entity.LoginFail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginFailDao extends JpaRepository<LoginFail,Long> {
}
