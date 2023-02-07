package org.datn.service;

import org.datn.entity.Comment;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CommentService {
     List<Comment> getComment(Optional<Integer> pageNumber,Long idCourse);
     Comment addComment(Comment comment);
     Boolean exists(Long idUser,Long idCourse);
     Object avgStar(Long idCourse);
     Integer getCountComment(Long idCourse,Short munberStar);
}
