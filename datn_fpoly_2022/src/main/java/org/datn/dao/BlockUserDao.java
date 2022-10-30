package org.datn.dao;

import org.datn.entity.BlockUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockUserDao extends JpaRepository<BlockUser,Long> {
}
