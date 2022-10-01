package org.datn.DAO;

import org.datn.entity.BlockUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockUserDao extends JpaRepository<BlockUser,Long> {
}
