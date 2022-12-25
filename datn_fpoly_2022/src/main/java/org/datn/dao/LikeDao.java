package org.datn.dao;

import org.datn.entity.Admin;
import org.datn.entity.Course;
import org.datn.entity.Likes;
import org.datn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface LikeDao extends JpaRepository<Likes, Long> {
    @Query("SELECT count(l.id) FROM Likes l WHERE l.course.id =:id")
    Integer getLikeOfCourse(@Param("id")long idCourse);

    @Query("SELECT l.course FROM Likes l WHERE l.user_id =:id")
    Course getLikeOfUser(@Param("id")long idUser);

    @Query("delete from Likes l where l.user_id =:id ")
    @Modifying()
    void deleteByUser_id(@Param("id")long idUser);

    @Query("select count(l.id) from Likes l where l.user_id =:user_id and l.course.id =:course")
    Integer existsByCourseAndUser_id(@Param("course")long course, @Param("user_id") long user_id);
}
