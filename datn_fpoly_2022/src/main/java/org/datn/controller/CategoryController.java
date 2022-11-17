package org.datn.controller;

import antlr.collections.impl.Vector;
import org.datn.dao.CategoryDao;
import org.datn.entity.Category;
import org.datn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity getAll(Model model) {
        List<Category>lst = categoryService.getAll();
        return ResponseEntity.ok(lst);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getId(@PathVariable("id") Long id) {
        Optional<Category> optional = categoryService.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        categoryService.save(category);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> put(
                                     @RequestBody Category category) {
        if (!categoryService.exists(category.getId())) {
            return ResponseEntity.notFound().build();
        }
        categoryService.put(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!categoryService.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("page/{pageNumber}/{pageSize}")
    public ResponseEntity page(
            @PathVariable("pageNumber") int pageNumber,
            @PathVariable("pageSize") int pageSize
    ){
        if(pageNumber < 0 || pageSize < 0)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryService.pageCategories(pageNumber,pageSize));
    }
}
