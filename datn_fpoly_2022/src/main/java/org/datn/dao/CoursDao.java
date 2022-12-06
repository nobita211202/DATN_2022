package org.datn.dao;

import org.datn.entity.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CoursDao extends JpaRepository<Cours,Long> {

    @Query("select cx from Cours cx where cx.status=0")
    public List<Cours>  getAllCourses();

    @Modifying
    @Transactional
    @Query("update Cours c set c.status = 1 where c.id =:id ")
    public void delete(@Param("id")long id );
}
