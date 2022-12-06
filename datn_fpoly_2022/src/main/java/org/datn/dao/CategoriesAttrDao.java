package org.datn.dao;

import org.datn.entity.CategoriesAttr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriesAttrDao extends JpaRepository<CategoriesAttr,Long> {
@Query(value= "SELECT ca.* FROM CATEGORIES_ATTR ca INNER JOIN \n" +
        "\t\t\t\tCATEGORIES  c \n" +
        "\t\t\t\tON c.category_id = ca.category_id\n" +
        "\t\t\t\tWHERE c.category_id =  ? ", nativeQuery = true)
List<CategoriesAttr> findCategoriesAttrByCategory(Long id);
}
