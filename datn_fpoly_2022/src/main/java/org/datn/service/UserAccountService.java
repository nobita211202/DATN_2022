package org.datn.service;



import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.datn.bean.ResponseData;
import org.datn.entity.Admin;
import org.datn.entity.BlockUser;
import org.datn.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.net.UnknownHostException;
import java.time.Instant;
import java.util.List;


public interface UserAccountService  {


    User findById(String email);

    BlockUser findByEmailInBlockUser(User u);

    BlockUser findByIpInBlockUser(String i,Instant d);

    BlockUser createIpInBlockUser(BlockUser bl);

    Instant findEffectUntilInBlockUser(String ip);
    List<User> findAll();
    Admin findByIdAdmin(String username);

    List<Admin> findAllAdmin();
    List<User> getAdministrators();


    ResponseData login(User user) throws UnknownHostException;
    User findOneByEmailIgnoreCaseAndPassword(String email, String password);
}
