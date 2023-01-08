package org.datn.service;

import org.datn.entity.User;

import java.util.List;

public interface UsersRoleService {
    List<User> getUserByRole(long idRole);
}
