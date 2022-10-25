package org.datn.service.Impl;

import org.datn.dao.UserDao;
import org.datn.entity.User;
import org.datn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return userDao.findById(id);
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public void delete(long id) {
        userDao.deleteById(id);
    }

    @Override
    public User put(User user) {
        return userDao.save(user);
    }

    @Override
    public boolean exists(long id) {
        return userDao.existsById(id);
    }
}
