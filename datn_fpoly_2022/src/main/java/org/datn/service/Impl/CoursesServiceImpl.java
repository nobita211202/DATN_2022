package org.datn.service.Impl;

import org.datn.dao.CoursDao;
import org.datn.entity.Cours;
import org.datn.service.CoursService;
import org.datn.utils.Base.Bases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CoursesServiceImpl implements CoursService {
    @Autowired
    CoursDao cdao;
    @Autowired
    Bases<Cours> bases;
    @Override
    public List<Cours> findAll() {
        return cdao.getAllCourses();
    }

    @Override
    public List<Cours> pagination() {
        return cdao.findAll();
    }

    @Override
    public Cours createCours(Cours co) {
        co.setStatus((short)0);
        return cdao.save(bases.getBase(co,Bases.CREATE));
    }

    @Override
    public void deleteCours(Long id) {
        cdao.delete(id);
    }

    @Override
    public Cours updateCours(Cours co) {
        return cdao.save(bases.getBase(co,Bases.UPDATE));
    }

    @Override
    public Cours findByIDCours(Long id) {
        return cdao.findById(id).get();
    }

    @Override
    public Page<Cours> getCoursPaging(Optional<Integer> pageNumber, Integer pageSize) {
        Sort sort = Sort.sort(Cours.class).by(Cours::getId).ascending();
        Pageable pageable = PageRequest.of(pageNumber.orElse(0),pageSize,sort);
        return cdao.findAll(pageable);
    }
}
