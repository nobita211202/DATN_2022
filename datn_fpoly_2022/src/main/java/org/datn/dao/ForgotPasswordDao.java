package org.datn.DAO;

import org.datn.entity.ForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgotPasswordDao extends JpaRepository<ForgotPassword,Long> {
}
