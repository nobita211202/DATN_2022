package org.datn.controller;

import org.datn.entity.Cours;
import org.datn.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/coursapi")
public class CoursController {
    @Autowired
    CoursService coursService;

    @GetMapping("/listcours")
    public List<Cours> getAll() {                 //Lấy danh sách khóa học
        return coursService.findAll();
    }
    @PostMapping("/addcours")
    public Cours addCours(@RequestBody Cours cours){    //Thêm khóa học
        return coursService.createCours(cours);
    }
    @DeleteMapping("/deletecours/{id}")
    public void deleteCours (@PathVariable("id") Long id){      //Xóa khóa học theo id
        coursService.deleteCours(id);
    }
    @PutMapping("/updatecours/{id}")
    public Cours updateCours (@PathVariable("id") Long id ,@RequestBody Cours cours){   //Update khóa học
        return coursService.updateCours(cours);
    }
    @GetMapping("{id}")
    public Cours getOne (@PathVariable("id") Long id){              //tìm kiếm
        return coursService.findByIDCours(id);
    }
    @GetMapping("/paging/{pageNumber}/{pageSize}")
    public Page<Cours> coursPagination(@PathVariable Optional<Integer> pageNumber,@PathVariable Integer pageSize){
        return coursService.getCoursPaging(pageNumber,pageSize);
    }

    @GetMapping("getimage/{image}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("image")String image){
        if (!image.equals("") || image !=null){
            try {
                Path filename= Paths.get("uploadCours",image);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok().contentType(MediaType.parseMediaType("images/jpg"))
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
