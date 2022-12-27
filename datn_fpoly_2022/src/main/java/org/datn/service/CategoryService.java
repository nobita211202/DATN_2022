package org.datn.service;


import org.datn.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAll();
    Optional findById(long id);
    Category save(Category category);
    void delete(long id);
    Category put(Category category);
    boolean exists(long id);
    List<Category> search(String name);
    Page<Category> pageCategories(int pageNumber ,int pageSize);
    List<Category> findCategoryByParentId(Long id);
    List<Category> getAllByParentIsNull();
}
