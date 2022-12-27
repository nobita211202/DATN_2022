package org.datn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.datn.entity.Course;
import org.datn.service.CoursService;
import org.datn.service.ImageService;
import org.datn.service.LikeService;
import org.datn.utils.Base.EntityAndImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/course")
@SuppressWarnings("all")
public class CoursController {

    @Autowired
    CoursService coursService;
    @Autowired
    LikeService likeService;
    @Autowired
    ImageService service;

    @GetMapping("/get")
    public ResponseEntity getAll() {                 //Lấy danh sách khóa học
        return ResponseEntity.ok(coursService.findAll());
    }
    @GetMapping("/getCourseAndLike/{user_id}")
    public ResponseEntity getCourseAnhLike(
            @PathVariable("user_id") long idUser
    ) {                 //Lấy danh sách khóa học
        return ResponseEntity.ok(coursService.findAll().stream().map(course -> new Object[]{
                    course,likeService.exists(course,idUser)
            }
        ));
    }
    @PostMapping("/add")
    public ResponseEntity<Course> addCours(@ModelAttribute EntityAndImage data) throws JsonProcessingException {    //Thêm khóa học
        System.out.println(data.getJson());
        Course course = (Course) new ObjectMapper().readValue(data.getJson(), Course.class);
        course.setImage(service.saveImage(data.getFile()));
        coursService.createCours(course);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCours (@PathVariable("id") Long id){      //Xóa khóa học theo id
        coursService.deleteCours(id);
    }
    @PutMapping("/update")
    public Course updateCours (@ModelAttribute EntityAndImage data) throws JsonProcessingException {   //Update khóa học
        Course course = (Course) new ObjectMapper().readValue(data.getJson(), Course.class);
        course.setImage(service.saveImage(data.getFile()));
        coursService.createCours(course);
        return coursService.updateCours(course);
    }
    @GetMapping("/get/{id}")
    public Course getOne (@PathVariable("id") Long id){              //tìm kiếm
        return coursService.findByIDCours(id);
    }
    @GetMapping("/paging/{pageNumber}/{pageSize}")
    public Page<Course> coursPagination(@PathVariable Optional<Integer> pageNumber, @PathVariable Integer pageSize){
        return coursService.getCoursPaging(pageNumber,pageSize);
    }

    @GetMapping("/search/{search}")
    public ResponseEntity search(
            @PathVariable("search")String name
    ){
        return ResponseEntity.ok(coursService.getByName(name));
    }
    @GetMapping("/search/")
    public ResponseEntity searchAll() {
        return ResponseEntity.ok(coursService.findAll());
    }
    @GetMapping("/get-by-purchase")
    public ResponseEntity findTop5CoursePurchase(){
        return ResponseEntity.ok(coursService.findTop5CoursePurchase().stream().map(course -> new Object[]{
                        course,null
                }
        ));
    }
}




