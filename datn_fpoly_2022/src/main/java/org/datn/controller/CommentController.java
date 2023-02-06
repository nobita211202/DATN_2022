package org.datn.controller;

import org.datn.entity.Comment;
import org.datn.service.CommentService;
import org.datn.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/get/{pageNumber}/{courseId}")
    public ResponseEntity getComment(
            @PathVariable("pageNumber") Optional<Integer> paInteger,
            @PathVariable("courseId") Long courseId
    ){
        return ResponseEntity.ok(commentService.getComment(paInteger,courseId));
    }

    @PostMapping("/add")
    public ResponseEntity add (
            @RequestBody()Comment comment
            ){
        Boolean isComment= orderDetailService.existsCourseInOrderDetail(comment.getUser().getId(),comment.getCourseId());
        if (isComment)
        return ResponseEntity.ok(commentService.addComment(comment));

        return ResponseEntity.status(400).build();
    }
    @GetMapping("/isComment/{idUser}/{idCourse}")
    public ResponseEntity isComment(
            @PathVariable("idUser") Long idUser,
            @PathVariable("idCourse") Long idCourse
    ){  if(orderDetailService.existsCourseInOrderDetail(idUser,idCourse) &&
            (!commentService.exists(idUser,idCourse))
        )
        return ResponseEntity.ok(true);
        return ResponseEntity.ok(false);
    }

    @GetMapping("/getCountComment/{idCourse}/{numberStar}")
    public ResponseEntity getCountComment(
            @PathVariable("numberStar") Short numberStar,
            @PathVariable("idCourse") Long idCourse
    ){

        return ResponseEntity.ok(commentService.getCountComment(idCourse,numberStar));
    }
//    @GetMapping("/isComment/{idCourse}/{idUser}")
//    public ResponseEntity getAvgStar(
//            @PathVariable("idUser") Long idUser,
//            @PathVariable("idCourse") Long idCourse
//    ){
//        return ResponseEntity.ok(orderDetailService.existsCourseInOrderDetail(idUser,idCourse));
//    }

    @GetMapping("/avgStar/{idCourse}")
    public ResponseEntity getAvgStar(
            @PathVariable("idCourse") Long idCourse
    ){
        Object obj = commentService.avgStar(idCourse);
        return ResponseEntity.ok(obj);
    }
}
