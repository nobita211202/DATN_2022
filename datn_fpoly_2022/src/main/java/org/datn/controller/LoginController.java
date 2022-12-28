package org.datn.controller;


import org.datn.bean.ResponseData;
import org.datn.entity.BlockUser;
import org.datn.entity.Token;
import org.datn.entity.User;
import org.datn.service.Impl.TokenServiceImpl;
import org.datn.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class LoginController {
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenServiceImpl tokenService;

    @Autowired
    HttpServletResponse response;
    int x=0;
    @GetMapping("/get")
    public List<User> getAccounts() {
        return userAccountService.findAll();
    }

    @GetMapping("/logined/{id}")
    public ResponseEntity checkLogin(
            @PathVariable("id") long id
    ){
        User user= userAccountService.findById(id);
        user.setPassword("");
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseData checkLogin(@RequestBody User user) throws Exception {
        String ip = InetAddress.getLocalHost().getHostAddress();            //lấy ip
        ResponseData<User> responseData = userAccountService.login(user);
        User u2 = userAccountService.findOneByEmailIgnoreCaseAndPassword(user.getEmail(), user.getPassword());
        if (responseData.getStatus()==200) {
            Token token = tokenService.findByUserId(user.getId());         //lấy token từ id
            if(token == null){
                token = new Token();
                token.setUser(u2);
                tokenService.create(token);                                 //tạo token
            }else{
                tokenService.delete(token.getId());                         //xóa token cũ
                token = new Token();
                token.setUser(u2);
                tokenService.create(token);                                 //tạo token mới
            }
            response.setHeader("Authorization", token.getTokenCode());           //set token vào header
           return new ResponseData("Successfully","Login success",u2, HttpStatus.OK.value(), token.getTokenCode()); //trả về token
        }
        if (!(responseData.getStatus()==200)&& x == 5 && userAccountService.findByEmail(user.getEmail())!=null && !(responseData.getStatus()==10)){
            x=0;
            responseData.setStatus(5000);
            BlockUser bl = new BlockUser();
            bl.setIpAddress(ip);                                                    //lấy ip và set
            bl.setCreated(Instant.now());
            bl.setEffectFrom(Instant.now());                                        //time hiện tại
            bl.setEffectUntil(Instant.now().plusSeconds(60*15));        //time kết thúc
            bl.setUser(userAccountService.findByEmail(user.getEmail()));
            userAccountService.createIpInBlockUser(bl);
            return new ResponseData("Error","Login failed",user,HttpStatus.FORBIDDEN.value(), tokenService.findByUserId(user.getId()).getTokenCode());
        }else if (!(responseData.getStatus()==200) && userAccountService.findByEmail(user.getEmail())!=null && !(responseData.getStatus()==10)){
            x++;
        }
        throw new RuntimeException("Login failed");
    }
    @GetMapping ("/token")
    public String token (){
        SecureRandom random = new SecureRandom();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();
        byte[] randomBytes = new byte[24];
        random.nextBytes(randomBytes);
        System.out.println(base64Encoder.encodeToString(randomBytes));
        return base64Encoder.encodeToString(randomBytes);
    }
}