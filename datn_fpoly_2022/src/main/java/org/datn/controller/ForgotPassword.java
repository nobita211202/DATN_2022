package org.datn.controller;

import org.datn.entity.User;
import org.datn.service.Impl.UserAccountServiceImpl;
import org.datn.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/forgotPassword")
@CrossOrigin("*")
public class ForgotPassword {
    @Autowired
    UserAccountService userAccountService;

    @GetMapping("/getExists/{usernameOrEmail}")
    public ResponseEntity getExistsUsernameOrEmail(
            @PathVariable("usernameOrEmail") String usernameOrEmail
    ){
        String email = userAccountService.existsByUsernameOrPassword(usernameOrEmail);
        if(email==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(email);
    }
    @GetMapping("/sendEmail/{email}")
    public ResponseEntity sendEmail(
            @PathVariable("email") String email
    )  {
        System.out.println(userAccountService.sendEmail(email));
        return ResponseEntity.ok(true);
    }
}
