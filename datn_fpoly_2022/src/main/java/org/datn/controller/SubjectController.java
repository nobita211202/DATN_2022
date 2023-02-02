package org.datn.controller;

import org.datn.service.ContactSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/subject")
@RestController()
@CrossOrigin("*")
public class SubjectController {
    @Autowired
    ContactSubjectService subjectService;

    @GetMapping("/get")
    public ResponseEntity get(){
        return ResponseEntity.ok(subjectService.getAll());
    }
}
