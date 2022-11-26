package org.datn.controller;

import org.datn.entity.Admin;
import org.datn.entity.Category;
import org.datn.entity.Role;
import org.datn.service.RoleService;
import org.datn.utils.Base.Bases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/get")
    public ResponseEntity<List<Role>> getAll(Model model) {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Role> getId(@PathVariable("id") Long id) {
        Optional<Role> optional = roleService.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("/add")
    public ResponseEntity<Role> add(@RequestBody Role role) {

        Admin admin= new Admin();
        admin.setId(1L);
        role.setAdmin(admin);
        roleService.save(role);
        return ResponseEntity.ok(role);
    }

    @PutMapping("/update")
    public ResponseEntity<Role> put(
            @RequestBody Role role) {
        if (!roleService.exists(role.getId())) {
            return ResponseEntity.notFound().build();
        }
        Admin admin= new Admin();
        admin.setId(1L);
        role.setAdmin(admin);
        roleService.put(role);
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!roleService.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page/{pageNumber}/{pageSize}")
    public ResponseEntity page(
            @PathVariable("pageNumber") int pageNumber,
            @PathVariable("pageSize") int pageSize
    ){
        if(pageNumber < 0 || pageSize < 0)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(roleService.pageCategories(pageNumber,pageSize));
    }

}
