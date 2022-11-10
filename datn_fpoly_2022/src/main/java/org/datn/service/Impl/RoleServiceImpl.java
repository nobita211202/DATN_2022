package org.datn.service.Impl;

import org.datn.dao.RoleDao;
import org.datn.entity.Role;
import org.datn.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> getAll() {
        return roleDao.findAll();
    }

    @Override
    public Optional findById(long id) {
        return roleDao.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }

    @Override
    public void delete(long id) {
        roleDao.delete_role(id);
    }

    @Override
    public Role put(Role role) {
        return roleDao.save(role);
    }

    @Override
    public boolean exists(long id) {
        return roleDao.existsById(id);
    }

    @Override
    public Page<Role> pageCategories(int pageNumber, int pageSize) {
        return roleDao.findAll(PageRequest.of(pageNumber,pageSize));
    }
}
