package org.datn.dao;

import org.datn.entity.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursDao extends JpaRepository<Cours,Long> {
    @Override
    @Query("update Cours c set c.status = 1 where c.id =:id ")
    void deleteById(@Param("id") Long id);

    @Override
    @Query("select c from Cours c where c.status = 0")
    List<Cours> findAll();
}
