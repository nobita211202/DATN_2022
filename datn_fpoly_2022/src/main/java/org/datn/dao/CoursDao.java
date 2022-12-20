package org.datn.dao;

import org.datn.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CoursDao extends JpaRepository<Course,Long> {
    @Override
    @Modifying
    @Query("update Course c set c.status = 1 where c.id =:id ")
    void deleteById(@Param("id") Long id);

    @Override
    @Query("select c from Course c where c.status = 0")
    List<Course> findAll();

    @Query("select c from Course c where c.status = 0 and  c.name like :name")
    List<Course> findAllByNamel(@Param("name") String name);


}
