package org.datn.controller;

import org.datn.entity.CategoriesAttr;
import org.datn.service.CategoryAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/category-attribute")
public class CategoryAttrController {
    @Autowired
    CategoryAttrService categoryAttrService;

    @GetMapping("/category-attribute/get")
    public List<CategoriesAttr> getAll(){
        return categoryAttrService.findAll();
    }
    @PostMapping("/category-attribute/add")
    public CategoriesAttr addCategoriesAttr(@RequestBody CategoriesAttr cattr){    //Thêm khóa học
        return categoryAttrService.createCategoryAttr(cattr);
    }
    @DeleteMapping("/category-attribute/delete/{id}")
    public void deleteCategoriesAttr (@PathVariable("id") Long id){      //Xóa khóa học theo id
        categoryAttrService.deleteCategoryAttr(id);
    }
    @PutMapping("/category-attribute/update/{id}")
    public CategoriesAttr updateCategoriAttr (@PathVariable("id") Long id ,@RequestBody CategoriesAttr cattr){   //Update khóa học
        return categoryAttrService.updateCategoryAttr(cattr);
    }
    @GetMapping("/findByID/{id}")
    public CategoriesAttr findByIDCategoriesAttr(@PathVariable("id") Long id){
        return categoryAttrService.findByIDCategoryAttr(id);
    }
    @GetMapping("/find-by-category-id/{id}")
    public List<CategoriesAttr> findCategoriesAttrByCategory(@PathVariable("id") Long id){
        return categoryAttrService.findCategoriesAttrByCategory(id);
    }
}
