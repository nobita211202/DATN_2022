package org.datn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @GetMapping("/get/{pageNumber}")
    public ResponseEntity getComment(){
        return ResponseEntity.ok("");
    }
}
