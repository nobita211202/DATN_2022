package org.datn.controller;

import org.datn.entity.Category;
import org.datn.service.CategoryService;
import org.datn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;

    @GetMapping("/get")
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
    public ResponseEntity<Category> add(
            @RequestBody Category category) {
        System.out.println(category);
        categoryService.save(category);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/update")
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
    @GetMapping("/find/parent/{id}")
    public ResponseEntity findParent(@PathVariable Long id){
        if(!categoryService.exists(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryService.findCategoryByParentId(id));
    }

    @GetMapping("/search/{txtSearch}")
    public ResponseEntity search(
            @PathVariable("txtSearch") String txtSearch
    ) {
        return ResponseEntity.ok(categoryService.search(txtSearch));
    }
    @GetMapping("/search/")
    public ResponseEntity searchAll() {
          return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/get/parent")
    public ResponseEntity findParent(){
        return ResponseEntity.ok(categoryService.getAllByParentIsNull());
    }
}
