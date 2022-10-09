package org.datn.controller;

import org.datn.DAO.CategoryDao;
import org.datn.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    CategoryDao cDao;

    @GetMapping("category/all")
    public ResponseEntity<List<Category>> getAll(Model model) {
        return ResponseEntity.ok(cDao.findAll());
    }

    @GetMapping("category/{id}")
    public ResponseEntity<Category> getId(@PathVariable("id") Long id) {
        Optional<Category> optional = cDao.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("category/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        if (cDao.existsById(category.getId())) {
            return ResponseEntity.badRequest().build();
        }
        cDao.save(category);
        return ResponseEntity.ok(category);
    }

    @PutMapping("category/{id}")
    public ResponseEntity<Category> put(@PathVariable("id") Long id,
                                     @RequestBody Category category) {
        if (!cDao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cDao.save(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("category/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!cDao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cDao.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
