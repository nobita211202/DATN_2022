package org.datn.service;


import org.datn.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAll();
    Optional findById(long id);
    Category save(Category category);
    void delete(long id);
    Category put(Category category);
    boolean exists(long id);
}
