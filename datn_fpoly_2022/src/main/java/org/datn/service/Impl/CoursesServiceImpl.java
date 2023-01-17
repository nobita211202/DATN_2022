package org.datn.service.Impl;

import org.datn.dao.CoursDao;
import org.datn.entity.Course;
import org.datn.service.CoursService;
import org.datn.utils.Base.Bases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CoursesServiceImpl implements CoursService {
    @Autowired
    CoursDao cdao;
    @Autowired
    Bases<Course> bases;

    @Override
    public List<Course> findAll() {
        return cdao.getByStatus((short)3);
    }

    @Override
    public List<Course> getCourseByUserId(long idUser) {
        return cdao.getCourseByUserId(idUser);
    }

    @Override
    public List<Course> pagination() {
        return cdao.findAll();
    }

    @Override
    public void setStatus(long idCourse,Short status) {
        cdao.setStatus(idCourse,status);
    }

    @Override
    public Course createCours(Course co) {
        co.setStatus((short)0);
        return cdao.save(bases.getBase(co,Bases.CREATE));
    }

    @Override
    public List<Course> getByStatus(Short status) {
        return cdao.getByStatus(status);
    }

    @Override
    public void deleteCours(Long id) {
        cdao.deleteById(id);
    }

    @Override
    public Course updateCours(Course co) {
        return cdao.save(bases.getBase(co,Bases.UPDATE));
    }

    @Override
    public List<Course> getMyCourse(long userId) {
        return cdao.getMyCourse(userId);
    }

    @Override
    public List<Course> getByName(String name) {
        String search= "%"+name+"%";
        System.out.println(search);

        return cdao.findAllByNamel(search);
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

    @Override
    public List<Course> findTop5CoursePurchase() {
        return cdao.findTop5CoursePurchase();
    }

    @Override
    public Collection<Course> getCourseByCategoryId(Long id) {
        return cdao.getCourseByCategoryId(id);
    }
}
