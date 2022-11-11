package org.datn.service.Impl;

import org.datn.dao.CoursDao;
import org.datn.entity.Cours;
import org.datn.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesServiceImpl implements CoursService {
    @Autowired
    CoursDao cdao;

    @Override
    public List<Cours> findAll() {
        return cdao.findAll();
    }

    @Override
    public List<Cours> pagination() {
        return cdao.findAll();
    }

    @Override
    public Cours createCours(Cours co) {
        return cdao.save(co);
    }

    @Override
    public void deleteCours(Long id) {
        cdao.deleteById(id);
    }

    @Override
    public Cours updateCours(Cours co) {
        return cdao.save(co);
    }

    @Override
    public Cours findByIDCours(Long id) {
        return cdao.findById(id).get();
    }

    @Override
    public Page<Cours> getCoursPaging(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return cdao.findAll(pageable);
    }
}
