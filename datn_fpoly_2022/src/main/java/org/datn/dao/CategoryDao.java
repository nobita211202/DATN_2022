package org.datn.dao;

import org.datn.entity.Category;
import org.datn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public interface CategoryDao extends JpaRepository<Category, Long> {
    List<Category> findCategoryByParent(Category category);
    @Query(value = "CALL delete_ctg(?1)", nativeQuery = true)
    @Modifying
    void delete_ctg(long id);

    /**
     * @param id of parent category
     *           find all category by parent id
     * @return
     *          list of category
     */
    @Query(value = "WITH RECURSIVE tempTable\n" +
            "as\n" +
            "\t(\n" +
            "\t\tSELECT c.category_id,c.parent_id,c.admin_id,c.name_,c.interest,c.like_,c.created,c.image,\n" +
            "\t\t\tc.creator,c.modified,c.modifier,c.status \n" +
            "\t\t\tFROM categories c WHERE c.parent_id = ?1\n" +
            "\t\tUNION ALL\n" +
            "\t\tSELECT c.category_id,c.parent_id,c.admin_id,c.name_,c.interest,c.like_,c.created,c.image,\n" +
            "\t\t\tc.creator,c.modified,c.modifier,c.status \n" +
            "\t\t\tFROM categories c \n" +
            "\t\t\tINNER JOIN tempTable t on t.category_id = c.parent_id\n" +
            "\t)\n" +
            "\tSELECT * FROM tempTable", nativeQuery = true)

    List<Category> findCategoryByParentId(Long id);
    @Query(value = "SELECT * FROM categories c WHERE c.parent_id IS NULL", nativeQuery = true)
    List<Category> getAllByParentIsNull();

    @Query("select c from Category c where   c.name like concat('%',:name,'%') ")
    List<Category> findAllByName(@Param("name") String name);

}
