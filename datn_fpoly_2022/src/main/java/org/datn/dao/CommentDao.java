package org.datn.dao;

import org.datn.entity.Comment;
import org.datn.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface CommentDao extends JpaRepository<Comment,Long> {

    @Query("select cmt from Comment cmt where cmt.courseId =:courseId")
    Page<Comment> findAll(Pageable pageable, @Param("courseId") Long courseId);

    @Query("select avg(cmt.numberStar),count(cmt.id) from Comment cmt where cmt.courseId =:idCourse  ")
    Object avgStar(@Param("idCourse")Long idCourse);

    Boolean existsByUserAndCourseId(User user,Long idCourse);

    @Query("select count(cmt) from Comment cmt where cmt.numberStar =:numberStar and cmt.courseId =:courseId")
    Integer getCountByNumberStar(@Param("courseId")Long courseId, @Param("numberStar")Short munberStar );
}
