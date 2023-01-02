package org.datn.controller;

import org.datn.entity.Course;
import org.datn.entity.Likes;
import org.datn.entity.User;
import org.datn.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/like")
@RestController
@CrossOrigin("*")
public class LikeController {
    @Autowired
    LikeService service;

    @GetMapping("/get/{idCourse}")
    public ResponseEntity getLike(
            @PathVariable("idCourse") Long id
    ){
        return ResponseEntity.ok(service.getAllOfCourseId(id));
    }

    @PostMapping("/add")
    public ResponseEntity addLike(
            @RequestBody() Likes like
    ){
        return ResponseEntity.ok(service.save(like));
    }

    @DeleteMapping("/delete/{idUser}/{idCourse}")
    public void deleteLike(
            @PathVariable("idUser") long idUser,
            @PathVariable("idCourse") long idCourse
    ){
        service.delete(idCourse,idUser);
    }

    @GetMapping("/exists/{idCourse}/{idUser}")
    public ResponseEntity getExists(
            @PathVariable("idCourse") long idCourse,
            @PathVariable("idUser") long idUser
    ){
        Course course= new Course();
        course.setId(idCourse);
        return ResponseEntity.ok(service.exists(course,idUser));
    }
}
