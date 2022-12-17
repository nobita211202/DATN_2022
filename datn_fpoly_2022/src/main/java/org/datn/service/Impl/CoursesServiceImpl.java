package org.datn.service.Impl;

import org.datn.dao.CoursDao;
import org.datn.entity.Course;
import org.datn.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesServiceImpl implements CoursService {
    @Autowired
    CoursDao cdao;

    @Override
    public List<Course> findAll() {
        return cdao.findAll();
    }

    @Override
    public List<Course> pagination() {
        return cdao.findAll();
    }

    @Override
    public Course createCours(Course co) {
        co.setStatus((short)0);
        return cdao.save(co);
    }

    @Override
    public void deleteCours(Long id) {
        cdao.deleteById(id);
    }

    @Override
    public Course updateCours(Course co) {
        return cdao.save(co);
    }

    @Override
    public List<Course> getByName(String name) {
        return cdao.findAllByNamel("%"+name+"%");
    }

    @Override
    public Course findByIDCours(Long id) {
        return cdao.findById(id).get();
    }

    @Override
    public Page<Course> getCoursPaging(Optional<Integer> pageNumber, Integer pageSize) {
        Sort sort = Sort.sort(Course.class).by(Course::getId).ascending();
        Pageable pageable = PageRequest.of(pageNumber.orElse(0),pageSize,sort);
        return cdao.findAll(pageable);
    }
}
