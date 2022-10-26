package org.datn.service.impl;


import org.datn.DAO.RoleDao;
import org.datn.entity.Role;
import org.datn.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao rdao;
    @Override
    public List<Role> findAll() {
        return rdao.findAll();
    }
}
