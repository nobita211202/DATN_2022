package org.datn.service;

import org.datn.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CoursService {
    List<Course> findAll();

    List<Course> pagination();

    Course createCours(Course co);

    void deleteCours(Long id);
    Course updateCours(Course co);

    List<Course> getByName(String name);
    Course findByIDCours(Long id);

    Page<Course> getCoursPaging(Optional<Integer> pageNumber, Integer pageSize);
}
