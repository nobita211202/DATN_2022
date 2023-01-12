package org.datn.service.Impl;


import org.datn.dao.BlockUserDao;
import org.datn.dao.UserDao;
import org.datn.bean.ResponseData;
import org.datn.entity.BlockUser;
import org.datn.entity.User;
import org.datn.service.UserAccountService;
import org.datn.utils.Base.Mail;
import org.datn.utils.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    UserDao udao;

    @Autowired
    BlockUserDao bldao;

    int x = 0;

    @Override
    public User findById(long id) {
        return udao.findById(id).get();
    }

    @Override
    public User findByEmail(String email) {
        return udao.findByEmail(email);
    }

    @Override
    public BlockUser findByEmailInBlockUser(User u) {
        return udao.findByEmailInBlockUser(u);
    }

    @Override
    public BlockUser findByIpInBlockUser(String i,Instant d) {
        return udao.findByIPInBlockUser(i,d);
    }

    @Override
    public BlockUser createIpInBlockUser(BlockUser bl) {
        return bldao.save(bl);
    }

    @Override
    public Instant findEffectUntilInBlockUser(String ip) {
        return udao.findEffectUntilInBlockUser(ip);
    }

    @Override
    public List<User> findAll() {
        return udao.findAll();
    }


    @Override
    public List<User> getAdministrators() {
        return udao.getAdministrators();
    }

    @Override
    public String existsByUsernameOrEmail(String usernameOrEmail) {
        User user= udao.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail);
        if (user == null) return null;
        return user.getEmail();
    }

    @Override
    public ResponseData login(User user) throws UnknownHostException {

        ResponseData responseData=new ResponseData();
        String ip = InetAddress.getLocalHost().getHostAddress();    //lấy ip

        User user1 = udao.findOneByEmailIgnoreCaseAndPassword(user.getEmail(),user.getPassword());  //sai email hoặc mật khẩu
        User user2=udao.findByEmail(user.getEmail());       //email không tồn tại
        Instant time_until=udao.findEffectUntilInBlockUser(ip);                 //lấy thời gian cuối
        BlockUser user4 = udao.findByIPInBlockUser(ip , time_until);             //tìm user block theo ip

        if (user4 != null  ){
            System.out.println(user4.getIpAddress());
            Instant time_now = Instant.now();                           //lấy time hiện tại
            System.out.println(time_now.isAfter(time_until));
            if (time_now.isAfter(time_until)==false){
                responseData.setStatus(HttpStatus.LOCKED.value());
                responseData.setMessage("Bị khóa trong 15'");
            }
        }else{
                if (user2 == null){
                    responseData.setStatus(HttpStatus.NOT_FOUND.value());
                    responseData.setMessage("Email không tồn tại");
                } else if (user1 == null) {
                    responseData.setStatus(HttpStatus.NO_CONTENT.value());
                    responseData.setMessage("Email hoặc mật khẩu sai");
                }else {
                    BlockUser user3 =udao.findByEmailInBlockUser(user2);
                    if (user3==null){
                        responseData.setStatus(HttpStatus.OK.value());
                        responseData.setMessage("Đăng nhập thành công");
                    }else {
                        System.out.println(user3.getUser().getEmail());
                        responseData.setStatus(HttpStatus.FORBIDDEN.value());
                        responseData.setMessage("Tài khoản đã bị khóa");
                    }
                }
            }


        responseData.setError(responseData.getMessage());
        return  responseData;
    }
    public User findOneByEmailIgnoreCaseAndPassword(String email, String password) {
        return udao.findOneByEmailIgnoreCaseAndPassword(email, password);
    }
    public String existsByUsernameOrPassword(String usernameAndEmail){
        User user= udao.findByUsernameOrEmail(usernameAndEmail,usernameAndEmail);
        if (user == null) return null;
        return user.getEmail();
    }
    public boolean sendEmail(String email)  {
        try {
            String pass=""+ Math.round(Math.random()*1236271);
            String title="Đổi mật khẩu";
            String content= Mail.docChangePassword(email,pass);
            System.out.println(content);
            MailSender.sendCode("hoangndph13827@fpt.edu.vn",content,title);
            //udao.repass(email,pass);
        }catch (Exception m){
            m.printStackTrace();
        }
        return true;
    }
}
//if (user2 == null){
//                responseData.setStatus(404);
//                responseData.setMessage("sai email hoặc mật khẩu");
//            } else if (user1 == null){
//                responseData.setStatus(201);
//
//                responseData.setMessage("email không tồn tại");
//            } else {
//                BlockUser user3=udao.findByEmailInBlockUser(user2);
//
//                if (user3 == null){
//                    responseData.setStatus(200);
//                }
//                else {
//                    responseData.setStatus(405);
//                    responseData.setMessage("Tài khoản đã bị khóa");
//                }
//
//            }
