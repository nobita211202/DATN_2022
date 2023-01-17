package org.datn.service;

import org.datn.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CoursService {
    List<Course> findAll();
    List<Course> getCourseByUserId(long userId);
    List<Course> pagination();
    void setStatus(long icCourse,Short status);
    Course createCours(Course co);
    List<Course> getByStatus(Short status);
    void deleteCours(Long id);
    Course updateCours(Course co);
    List<Course> getMyCourse(long userId);
    List<Course> getByName(String name);
    Course findByIDCours(Long id);

    Page<Course> getCoursPaging(Optional<Integer> pageNumber, Integer pageSize);
    List<Course> findTop5CoursePurchase();
}
