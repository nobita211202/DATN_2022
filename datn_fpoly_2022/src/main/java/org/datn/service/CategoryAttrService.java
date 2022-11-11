package org.datn.service;

import org.datn.entity.CategoriesAttr;
import org.datn.entity.Cours;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryAttrService {

    List<CategoriesAttr> findAll();
    CategoriesAttr createCategoryAttr(CategoriesAttr ca);

    void deleteCategoryAttr(Long id);
    CategoriesAttr updateCategoryAttr(CategoriesAttr ca);

    CategoriesAttr findByIDCategoryAttr(Long id);



}
