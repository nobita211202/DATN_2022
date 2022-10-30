package org.datn.controller;

import org.datn.dao.AdminDao;
import org.datn.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    AdminDao aDao;

    @GetMapping("admin/all")
    public ResponseEntity<List<Admin>> getAll(Model model) {
        return ResponseEntity.ok(aDao.findAll());
    }

    @GetMapping("admin/{id}")
    public ResponseEntity<Admin> getId(@PathVariable("id") Long id) {
        Optional<Admin> optional = aDao.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("admin/add")
    public ResponseEntity<Admin> add(@RequestBody Admin admin) {
        if (aDao.existsById(admin.getId())) {
            return ResponseEntity.badRequest().build();
        }
        aDao.save(admin);
        return ResponseEntity.ok(admin);
    }

    @PutMapping("admin/{id}")
    public ResponseEntity<Admin> put(@PathVariable("id") Long id,
                                        @RequestBody Admin admin) {
        if (!aDao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        aDao.save(admin);
        return ResponseEntity.ok(admin);
    }

    @DeleteMapping("admin/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!aDao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        aDao.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
