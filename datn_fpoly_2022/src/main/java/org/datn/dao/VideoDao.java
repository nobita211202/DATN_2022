package org.datn.dao;

import org.datn.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VideoDao extends JpaRepository<Video,Long> {
    @Query("select v from Video v where v.courseId = :courseId")
    List<Video> getVideoByCourse(@Param("courseId") long courseId);
}
