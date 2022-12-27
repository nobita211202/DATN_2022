package org.datn.service.Impl;

import org.datn.dao.CategoryDao;
import org.datn.entity.Category;
import org.datn.service.CategoryService;
import org.datn.utils.Base.Bases;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    Bases<Category> bases;

    @Override
    public List<Category> getAll() {
        return categoryDao.findAll();
    }

    @Override
    public Optional findById(long id) {
        return categoryDao.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryDao.save(bases.getBase(category,Bases.CREATE));
    }

    @Override
    public void delete(long id) {
        categoryDao.delete_ctg(id);
    }

    @Override
    public Category put(Category category) {
        return categoryDao.save(bases.getBase(category,Bases.UPDATE));
    }

    @Override
    public boolean exists(long id) {
        return categoryDao.existsById(id);
    }

    @Override
    public List<Category> search(String name) {
        return categoryDao.findAllByName(name);
    }

    @Override
    public Page<Category> pageCategories(int pageNumber,int pageSize) {
        return categoryDao.findAll(PageRequest.of(pageNumber,pageSize));
    }

    @Override
    public List<Category> findCategoryByParentId(Long id) {
        return categoryDao.findCategoryByParentId(id);
    }

    @Override
    public List<Category> getAllByParentIsNull() {

        return categoryDao.getAllByParentIsNull();
    }
}
