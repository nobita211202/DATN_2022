package org.datn.dao;

import org.datn.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public interface CoursDao extends JpaRepository<Course,Long> {
    @Override
    @Modifying
    @Query("update Course c set c.status = 0 where c.id =:id ")
    void deleteById(@Param("id") Long id);

    @Query("select c from Course c where c.user.id =:userId and c.status > 0")
    List<Course> getCourseByUserId(@Param("userId")long id );



    @Query("select c from Course c where c.status =:status")
    List<Course> getByStatus(@Param("status") Short status);

    @Modifying
    @Query("update Course c set c.status =:status where c.id =:id ")
    void setStatus(@Param("id") Long id,@Param("status")Short status );

    @Query("select c from Course c where c.status = 0 and  c.name like :name")
    List<Course> findAllByNamel(@Param("name") String name);
    @Query(value = "WITH course_purchase AS \n" +
            "(\n" +
            "\tSELECT od.price price,od.course_id,COUNT(od.course_id) pucharse\n" +
            "\tFROM orders_detail od \n" +
            "\tINNER JOIN orders o \n" +
            "\t\tON od.order_id = o.order_id\n" +
            "\tLEFT OUTER JOIN courses c \n" +
            "\t\tON od.course_id =  c.course_id\n" +
            "\tWHERE c.status = 0 \n" +
            "\tAND o.status = 0\n" +
            "\tGROUP BY \n" +
            "\t(\n" +
            "\t\tod.price,od.course_id\n" +
            "\t)\n" +
            "\tORDER BY 3 DESC\n" +
            "\tLIMIT 5\n" +
            ") SELECT c.* FROM courses c INNER JOIN course_purchase cp \n" +
            "ON c.course_id = cp.course_id\n",nativeQuery = true)
    List<Course> findTop5CoursePurchase();
    @Query(value = "WITH RECURSIVE temp_ \n" +
            "AS\n" +
            "(\n" +
            "\tSELECT c.category_id,c.parent_id \n" +
            "\tFROM categories c WHERE c.category_id = ?1\n" +
            "\tUNION ALL \n" +
            "\tSELECT c.category_id,c.parent_id\n" +
            "\tFROM categories c INNER JOIN temp_ t \n" +
            "\tON t.category_id  = c.parent_id\n" +
            "), category_attr AS\n" +
            "(\n" +
            "\tSELECT * FROM categories_attr ca \n" +
            "\tWHERE ca.category_id IN (\n" +
            "\t\tSELECT category_id FROM temp_\n" +
            "\t)\n" +
            "\t\n" +
            "),\n" +
            "result_final AS\n" +
            "(\n" +
            "\tSELECT * FROM courses c \n" +
            "\tINNER JOIN categories_attr ca\n" +
            "\tON ca.category_attr_id = c.category_attr_id\n" +
            ")\n" +
            "SELECT * FROM result_final rf \n")
    Collection<Course> getCourseByCategoryId(Long categoryId);

}
