package org.datn.controller;

import org.datn.entity.User;
import org.datn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class SignInController {
    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity signin(
            @RequestBody User user
            ){
        userService.save(user);
        return ResponseEntity.ok("");
    }
}
