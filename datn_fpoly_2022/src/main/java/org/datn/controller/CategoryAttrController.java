package org.datn.controller;

import org.datn.entity.CategoriesAttr;
import org.datn.entity.Cours;
import org.datn.service.CategoryAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/categoryattrapi")
public class CategoryAttrController {
    @Autowired
    CategoryAttrService categoryAttrService;

    @GetMapping("/listCategoriAttr")
    public List<CategoriesAttr> getAll(){
        return categoryAttrService.findAll();
    }
    @PostMapping("/addCategoriAttr")
    public CategoriesAttr addCours(@RequestBody CategoriesAttr cattr){    //Thêm khóa học
        return categoryAttrService.createCategoryAttr(cattr);
    }
    @DeleteMapping("/deleteCategoriAttr/{id}")
    public void deleteCours (@PathVariable("id") Long id){      //Xóa khóa học theo id
        categoryAttrService.deleteCategoryAttr(id);
    }
    @PutMapping("/updateCategoriAttr/{id}")
    public CategoriesAttr updateCategoriAttr (@PathVariable("id") Long id ,@RequestBody CategoriesAttr cattr){   //Update khóa học
        return categoryAttrService.updateCategoryAttr(cattr);
    }
}
