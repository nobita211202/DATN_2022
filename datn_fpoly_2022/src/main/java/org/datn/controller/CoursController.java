package org.datn.controller;

import org.datn.entity.Cours;
import org.datn.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/course")
public class CoursController {
    @Autowired
    CoursService coursService;

    @GetMapping("/get")
    public List<Cours> getAll() {                 //Lấy danh sách khóa học
        return coursService.findAll();
    }
    @PostMapping("/create")
    public Cours addCours(@RequestBody Cours cours){    //Thêm khóa học
        return coursService.createCours(cours);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCours (@PathVariable("id") Long id){      //Xóa khóa học theo id
        coursService.deleteCours(id);
    }
    @PutMapping("/update/{id}")
    public Cours updateCours (@PathVariable("id") Long id ,@RequestBody Cours cours){   //Update khóa học
        return coursService.updateCours(cours);
    }
    @GetMapping("/get/{id}")
    public Cours getOne (@PathVariable("id") Long id){              //tìm kiếm
        return coursService.findByIDCours(id);
    }
    @GetMapping("/paging/{pageNumber}/{pageSize}")
    public Page<Cours> coursPagination(@PathVariable Integer pageNumber,@PathVariable Integer pageSize){
//        pageSize=2;
        return coursService.getCoursPaging(pageNumber,pageSize);
    }
}
