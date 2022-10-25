package org.datn.service.Impl;

import org.datn.dao.CategoryDao;
import org.datn.entity.Category;
import org.datn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;
    @Override
    public List<Category> getAll() {
        return categoryDao.findAll();
    }

    @Override
    public Optional findById(long id) {
        return categoryDao.findById(id);
    }

    @Override
    public Category save(Category admin) {
        return categoryDao.save(admin);
    }

    @Override
    public void delete(long id) {
        categoryDao.deleteById(id);
    }

    @Override
    public Category put(Category admin) {
        return categoryDao.save(admin);
    }

    @Override
    public boolean exists(long id) {
        return categoryDao.existsById(id);
    }
}
