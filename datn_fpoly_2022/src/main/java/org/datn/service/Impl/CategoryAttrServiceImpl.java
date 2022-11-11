package org.datn.service.Impl;

import org.datn.dao.CategoriesAttrDao;
import org.datn.entity.CategoriesAttr;
import org.datn.service.CategoryAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryAttrServiceImpl implements CategoryAttrService {

    @Autowired
    CategoriesAttrDao cdao;

    @Override
    public List<CategoriesAttr> findAll() {
        return cdao.findAll();
    }

    @Override
    public CategoriesAttr createCategoryAttr(CategoriesAttr ca) {
        return cdao.save(ca);
    }

    @Override
    public void deleteCategoryAttr(Long id) {
        cdao.deleteById(id);
    }

    @Override
    public CategoriesAttr updateCategoryAttr(CategoriesAttr ca) {
        return cdao.save(ca);
    }

    @Override
    public CategoriesAttr findByIDCategoryAttr(Long id) {
        return cdao.findById(id).get();
    }
}
