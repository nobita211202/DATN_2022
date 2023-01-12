package org.datn.dao;

import org.datn.entity.CourseAttr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CourseAttrDao extends JpaRepository<CourseAttr,Long> {
    CourseAttr findByUrl(String url);
    Collection<CourseAttr> findByCourseId(Long courseId);
}
