package org.datn.DAO;

import org.datn.entity.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursDao extends JpaRepository<Cours,Long> {
}
