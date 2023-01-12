package org.datn.service;



import org.datn.bean.ResponseData;
import org.datn.entity.BlockUser;
import org.datn.entity.User;

import java.net.UnknownHostException;
import java.time.Instant;
import java.util.List;


public interface UserAccountService  {


    User findById(long id);
    User findByEmail(String email);


    BlockUser findByEmailInBlockUser(User u);

    BlockUser findByIpInBlockUser(String i,Instant d);

    BlockUser createIpInBlockUser(BlockUser bl);

    Instant findEffectUntilInBlockUser(String ip);
    List<User> findAll();

    List<User> getAdministrators();

    String existsByUsernameOrEmail(String usernameOrPassword);
    boolean sendEmail(String email);

    ResponseData login(User user) throws UnknownHostException;
    User findOneByEmailIgnoreCaseAndPassword(String email, String password);
}
