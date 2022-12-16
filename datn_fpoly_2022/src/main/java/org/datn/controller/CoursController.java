package org.datn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.datn.entity.Cours;

import org.datn.service.CoursService;
import org.datn.service.ImageService;
import org.datn.utils.Base.EntityAndImage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/course")
public class CoursController {
    @Autowired
    CoursService coursService;

    @Autowired
    ImageService service;

    @GetMapping("/get")
    public ResponseEntity getAll() {                 //Lấy danh sách khóa học
        return ResponseEntity.ok(coursService.findAll());
    }
    @PostMapping("/add")
    public ResponseEntity<Cours> addCours(@ModelAttribute EntityAndImage data) throws JsonProcessingException {    //Thêm khóa học
        System.out.println(data.getJson());
        Cours cours = (Cours) new ObjectMapper().readValue(data.getJson(),Cours.class);
        cours.setImage(service.saveImage(data.getFile()));
        coursService.createCours(cours);
        return ResponseEntity.ok(cours);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCours (@PathVariable("id") Long id){      //Xóa khóa học theo id
        coursService.deleteCours(id);
    }
    @PutMapping("/update")
    public Cours updateCours (@ModelAttribute EntityAndImage data) throws JsonProcessingException {   //Update khóa học
        Cours cours = (Cours) new ObjectMapper().readValue(data.getJson(),Cours.class);
        cours.setImage(service.saveImage(data.getFile()));
        coursService.createCours(cours);
        return coursService.updateCours(cours);
    }
    @GetMapping("/get/{id}")
    public Cours getOne (@PathVariable("id") Long id){              //tìm kiếm
        return coursService.findByIDCours(id);
    }
    @GetMapping("/paging/{pageNumber}/{pageSize}")
    public Page<Cours> coursPagination(@PathVariable Optional<Integer> pageNumber,@PathVariable Integer pageSize){
        return coursService.getCoursPaging(pageNumber,pageSize);
    }
}




