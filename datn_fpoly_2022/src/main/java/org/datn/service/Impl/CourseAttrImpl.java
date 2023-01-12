package org.datn.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.datn.dao.CourseAttrDao;
import org.datn.entity.CourseAttr;
import org.datn.service.CourseAttrService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
@Service
@RequiredArgsConstructor
@Slf4j(topic = "Course Attribute say : ")
public class CourseAttrImpl implements CourseAttrService {
    private final CourseAttrDao courseAttrDao;
    @Override
    public CourseAttr save(CourseAttr entity) {

        log.info("saving course attribute with url :  {}", entity.getUrl());
        return courseAttrDao.save(entity);
    }

    @Override
    public CourseAttr update(Long id, CourseAttr entity) {
        CourseAttr courseAttr = courseAttrDao.findById(id).orElseThrow(()-> new RuntimeException("Course attribute id not exist : "+ id));
        courseAttr.setTitle(entity.getTitle());
        courseAttr.setUrl(entity.getUrl());
        courseAttr.setDescribe(entity.getDescribe());
        return courseAttrDao.save(courseAttr);
    }

    @Override
    public Map deleteById(Long id) {
        try {
            courseAttrDao.deleteById(id);
            return Map.of("message","Xóa thành công","status", HttpStatus.OK.value());
        }catch (Exception e){
            return Map.of("error",e.getMessage(),"status",HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public CourseAttr findById(Long id) {
        return courseAttrDao.findById(id).orElseThrow(()-> new RuntimeException("course not found"));
    }

    @Override
    public CourseAttr findByUrl(String url) {
        return courseAttrDao.findByUrl(url);
    }

    @Override
    public Collection<CourseAttr> findByCourseId(Long courseId) {
        return courseAttrDao.findByCourseId(courseId);
    }
}
