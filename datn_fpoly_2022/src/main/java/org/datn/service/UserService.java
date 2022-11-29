package org.datn.service;


import org.datn.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();
    Optional<User> findById(long id);
    User save(User user);
    void delete(long id);
    User put(User user);
    boolean exists(long id);

    User findByEmail(String email);
}
