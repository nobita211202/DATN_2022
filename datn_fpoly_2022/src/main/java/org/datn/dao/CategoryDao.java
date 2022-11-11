package org.datn.dao;

import org.datn.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {
    List<Category> findCategoryByParent(Category category);

    @Procedure()
    void delete_ctg(long id);
}
