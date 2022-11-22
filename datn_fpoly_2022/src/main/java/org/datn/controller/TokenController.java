package org.datn.controller;

import org.datn.entity.Token;
import org.datn.service.Impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/token")
public class TokenController {
    @Autowired
    TokenServiceImpl tokenService;
    @PostMapping("/add")
    public Token create(@RequestBody Token token) {
        return tokenService.create(token);
    }
    @GetMapping("/get/{token}")
    public Token findByToken(@PathVariable String token) {
        return tokenService.findByToken(token);
    }
    @GetMapping("/get/user/{id}")
    public Token findByUserId(@PathVariable Long id) {
        return tokenService.findByUserId(id);
    }
    @GetMapping("/get/{id}")
    public Token findById(@PathVariable Long id) {
        return tokenService.findById(id);
    }
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        tokenService.delete(id);
    }

}
