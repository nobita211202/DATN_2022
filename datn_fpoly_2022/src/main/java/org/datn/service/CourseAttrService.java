package org.datn.service;

import org.datn.entity.CourseAttr;

import java.util.Collection;
import java.util.Map;

public interface CourseAttrService {
    CourseAttr save(CourseAttr entity);
    CourseAttr update(Long id,CourseAttr entity);
    Map deleteById(Long id);
    CourseAttr findById(Long id);
    CourseAttr findByUrl(String url);
    Collection<CourseAttr> findByCourseId(Long courseId);

}
