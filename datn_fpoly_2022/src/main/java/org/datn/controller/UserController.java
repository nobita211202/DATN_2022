package org.datn.controller;

import org.datn.DAO.CategoryDao;
import org.datn.DAO.UserDao;
import org.datn.entity.Category;
import org.datn.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserDao uDao;

    @GetMapping("user/all")
    public ResponseEntity<List<User>> getAll(Model model) {
        return ResponseEntity.ok(uDao.findAll());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> getId(@PathVariable("id") Long id) {
        Optional<User> optional = uDao.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("user/add")
    public ResponseEntity<User> add(@RequestBody User user) {
        if (uDao.existsById(user.getId())) {
            return ResponseEntity.badRequest().build();
        }
        uDao.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("user/{id}")
    public ResponseEntity<User> put(@PathVariable("id") Long id,
                                        @RequestBody User user) {
        if (!uDao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        uDao.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!uDao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        uDao.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
