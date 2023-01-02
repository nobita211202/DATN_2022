package org.datn.service.Impl;

import org.datn.dao.LikeDao;
import org.datn.entity.Course;
import org.datn.entity.Likes;
import org.datn.entity.User;
import org.datn.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeDao likeDao;

    @Override
    public Integer getAllOfCourseId(long idCourse) {
        return likeDao.getLikeOfCourse(idCourse);
    }

    @Override
    public Likes save(Likes like) {
        return likeDao.save(like);
    }

    @Override
    public void delete(long idCourse,long idUser) {
        likeDao.deleteByUser_id(idCourse,idUser);
    }

    @Override
    public boolean exists(Course course, long user_id) {
        System.out.println(likeDao.existsByCourseAndUser_id(course.getId(),user_id));
        return likeDao.existsByCourseAndUser_id(course.getId(),user_id) < 1 ? false : true;
    }
}
