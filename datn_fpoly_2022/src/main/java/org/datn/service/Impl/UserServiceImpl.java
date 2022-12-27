package org.datn.service.Impl;

import org.datn.dao.UserDao;
import org.datn.entity.User;
import org.datn.service.UserService;
import org.datn.utils.Base.Bases;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    Bases<User> bases;
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
        user.setImage("default-user.jpg");
        return userDao.save(bases.getBase(user,Bases.CREATE));
    }

    @Override
    public void delete(long id) {
        userDao.deleteById(id);
    }

//    @Override
//    public User findUserByToken(String token) {
//        return userDao.findUserByToken(token);
//    }

    public List<User> search(String txtSearch){
        return userDao.findAllByNameOrUsername(txtSearch,txtSearch);
    };

    @Override
    public User put(User user) {
        return userDao.save(bases.getBase(user,Bases.UPDATE));
    }

    @Override
    public boolean exists(long id) {
        return userDao.existsById(id);
    }
}
