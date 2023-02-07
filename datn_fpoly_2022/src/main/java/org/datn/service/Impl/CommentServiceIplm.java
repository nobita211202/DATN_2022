package org.datn.service.Impl;

import org.datn.dao.CommentDao;
import org.datn.entity.Comment;
import org.datn.entity.Course;
import org.datn.entity.User;
import org.datn.service.CommentService;
import org.datn.utils.Base.Bases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class CommentServiceIplm implements CommentService {
    @Autowired
    CommentDao commentDao;
    @Autowired
    Bases<Comment> base;
    @Override
    public List<Comment> getComment(Optional<Integer> pageNumber,Long courseId) {
        Sort sort = Sort.sort(Comment.class).by(Comment::getId).descending();
        Pageable pageable = PageRequest.of(pageNumber.orElse(0), 3,sort);
        Page page = commentDao.findAll(pageable);
        return commentDao.findAll(pageable,courseId).getContent();
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentDao.save(base.getBase(comment,Bases.CREATE));
    }

    @Override
    public Boolean exists(Long idUser, Long idCourse) {
        User user = new User();
        user.setId(idUser);
        return commentDao.existsByUserAndCourseId(user,idCourse);
    }

    @Override
    public Object avgStar(Long idCourse) {
        return commentDao.avgStar(idCourse);
    }

    @Override
    public Integer getCountComment(Long idCourse, Short munberStar) {
        return commentDao.getCountByNumberStar(idCourse,munberStar);
    }
}
