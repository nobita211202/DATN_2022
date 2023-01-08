package org.datn.service.Impl;

import org.datn.dao.UsersRoleDao;
import org.datn.entity.User;
import org.datn.service.UsersRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserRoleServiceImpl implements UsersRoleService {
    @Autowired
    UsersRoleDao usersRoleDao;
    @Override
    public List<User> getUserByRole(long id) {
        return usersRoleDao.getUserByIdRole(id);
    }
}
