package org.datn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.datn.entity.User;
import org.datn.service.ImageService;
import org.datn.service.UserService;
import org.datn.service.UsersRoleService;
import org.datn.utils.Base.EntityAndImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
@SuppressWarnings("unused")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UsersRoleService usersRoleService;
    @Autowired
    ImageService service;
    @GetMapping("/get")
    public ResponseEntity<List<User>> getAll(Model model) {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id).orElse(null));
    }

    @GetMapping("/getByRole/{roleId}")
    public ResponseEntity getByRole(@PathVariable("roleId") Long id) {
        return ResponseEntity.ok(usersRoleService.getUserByRole(id));
    }

    @GetMapping("/search/{txtSearch}")
    public ResponseEntity search(@PathVariable("txtSearch") String txtSearch) {
        if(txtSearch.isEmpty()) return ResponseEntity.ok(userService.getAll());
        return ResponseEntity.ok(userService.search(txtSearch));
    }
    @GetMapping("/search/")
    public ResponseEntity searchAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<User> add(
        @ModelAttribute EntityAndImage data
            ) throws IOException {
        User user = (User) new ObjectMapper().readValue(data.getJson(),User.class);
        user.setImage(data.getFile() == null ? "default-user.jpg" : service.saveImage(data.getFile()) );
        System.out.println(user.getImage());
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<User> put(
            @ModelAttribute EntityAndImage data
    ) throws JsonProcessingException {
        User user = (User) new ObjectMapper().readValue(data.getJson(),User.class);
        user.setImage(data.getFile() == null ? null : service.saveImage(data.getFile()) );
        if(user.getPassword() == null) {
            User user1 = userService.findById(user.getId()).get();
            user.setPassword(user1.getPassword());
            user.setUsername(user1.getUsername());
            if(user.getImage() == null){
                user.setImage(user1.getImage());
            }
        }
        userService.put(user);
        return ResponseEntity.ok(user);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (!userService.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
