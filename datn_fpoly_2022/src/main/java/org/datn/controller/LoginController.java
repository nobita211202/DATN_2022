package org.datn.controller;

import org.datn.dao.BlockUserDao;
import org.datn.bean.ResponseData;
import org.datn.entity.BlockUser;
import org.datn.entity.User;
import org.datn.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.time.Instant;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class LoginController {
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    AuthenticationManager authenticationManager;
    int x=0;
    @GetMapping("/list")
    public List<User> getAccounts() {
        return userAccountService.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData> checkLogin(@RequestBody User user) throws Exception {
        String ip = InetAddress.getLocalHost().getHostAddress();            //lấy ip
        ResponseData responseData = userAccountService.login(user);         //lấy dữ liệu từ login
        if (responseData.getStatus()==200) {
            responseData.setValue(userAccountService.findById(user.getEmail()));
        }
        if (!(responseData.getStatus()==200)&& x == 5 && userAccountService.findById(user.getEmail())!=null && !(responseData.getStatus()==10)){
            x=0;
            responseData.setStatus(5000);
            BlockUser bl = new BlockUser();
            bl.setIpAddress(ip);                                                    //lấy ip và set
            bl.setCreated(Instant.now());
            bl.setEffectFrom(Instant.now());                                        //time hiện tại
            bl.setEffectUntil(Instant.now().plusSeconds(60*15));        //time kết thúc
            bl.setUser(userAccountService.findById(user.getEmail()));
            userAccountService.createIpInBlockUser(bl);
        }else if (!(responseData.getStatus()==200) && userAccountService.findById(user.getEmail())!=null && !(responseData.getStatus()==10)){
            x++;
        }


        return ResponseEntity.ok(responseData);
    }
}