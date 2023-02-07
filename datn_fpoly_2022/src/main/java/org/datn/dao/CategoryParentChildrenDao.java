package org.datn.dao;

import org.datn.entity.CategoryParentChildren;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryParentChildrenDao extends JpaRepository<CategoryParentChildren,Integer> {
    @Query("select c from CategoryParentChildren c where c.parent is null and c.status = 0")
    List<CategoryParentChildren> getParent();
}
