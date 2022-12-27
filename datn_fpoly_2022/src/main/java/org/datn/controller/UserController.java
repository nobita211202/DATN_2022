package org.datn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.datn.dao.UserDao;
import org.datn.entity.User;
import org.datn.service.ImageService;
import org.datn.service.UserService;
import org.datn.utils.Base.Bases;
import org.datn.utils.Base.EntityAndImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ImageService service;
    @GetMapping("/get")
    public ResponseEntity<List<User>> getAll(Model model) {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getId(@PathVariable("id") Long id) {
        Optional<User> optional = userService.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
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
        user.setImage(service.saveImage(data.getFile()));
        System.out.println(user.getImage());
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<User> put(
            @ModelAttribute EntityAndImage data
    ) throws JsonProcessingException {
        User user = (User) new ObjectMapper().readValue(data.getJson(),User.class);
        user.setImage(service.saveImage(data.getFile()));
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
