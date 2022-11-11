package org.datn.service;

import org.datn.entity.Role;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAll();
    Optional findById(long id);
    Role save(Role Role);
    void delete(long id);
    Role put(Role Role);
    boolean exists(long id);
    Page<Role> pageCategories(int pageNumber , int pageSize);
}
