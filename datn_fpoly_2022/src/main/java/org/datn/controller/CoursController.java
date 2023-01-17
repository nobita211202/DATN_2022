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

    @GetMapping("/getCourseByUserId/{id}")
    public ResponseEntity getCourseByUserId(
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(coursService.getCourseByUserId(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Course> addCours(@ModelAttribute EntityAndImage data) throws JsonProcessingException {    //Thêm khóa học
        System.out.println(data.getJson());
        Course course = (Course) new ObjectMapper().readValue(data.getJson(), Course.class);
        course.setImage(service.saveImage(data.getFile()));
        course.setStatus((short)1);
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
        course.setImage(data.getFile() == null ? null : service.saveImage(data.getFile()));
        if(course.getImage() == null) {
            Course course1= coursService.findByIDCours(course.getId());
            course.setImage(course1.getImage());
        }
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
        return ResponseEntity.ok(coursService.findTop5CoursePurchase());
    }

    @GetMapping("/accept/{idCourse}")
    public void accept(
            @PathVariable("idCourse")  long idCourse
    ){
        coursService.setStatus(idCourse, (short)3);
    }

    @GetMapping("/not_accept/{idCourse}")
    public void notAccept(
            @PathVariable("idCourse")  long idCourse
    ){
        coursService.setStatus(idCourse, (short)2);
    }

    @GetMapping("/get/accept")
    public ResponseEntity getAccept(){
        return ResponseEntity.ok(coursService.getByStatus((short)3));
    }
    @GetMapping("/get/accept/{userId}")
    public void getAcceptByUserId(){
        coursService.getByStatus((short)3);
    }

    @GetMapping("/get/not_accept")
    public ResponseEntity getNotAccept(){
        return ResponseEntity.ok(coursService.getByStatus((short)2));
    }
    @GetMapping("/get/toAccept")
    public ResponseEntity getToAccept(){
        return ResponseEntity.ok(coursService.getByStatus((short)1));
    }
    @GetMapping("/get/myCourse/{idUser}")
    public ResponseEntity getMyCourse(@PathVariable("idUser") Long idUser ){
        return ResponseEntity.ok(coursService.getMyCourse(idUser));
    }


}




