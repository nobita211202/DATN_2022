package org.datn.service;

import org.datn.entity.Course;
import org.datn.entity.Likes;
import org.datn.entity.User;

public interface LikeService {
    Integer getAllOfCourseId(long id);
    Likes save(Likes like);
    void delete(long idCourse,long iduser);
    boolean exists(Course course, long user_id);
}
