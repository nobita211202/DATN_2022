package org.datn.service;

import org.datn.entity.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    List<Admin> getAll();
    Optional<Admin> findById(long id);
    Admin save(Admin admin);
    void delete(long id);
    Admin put(Admin admin);
    boolean exists(long admin);
}
