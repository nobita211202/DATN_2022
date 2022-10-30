package org.datn.dao;

import org.datn.entity.IpAddressUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpAddressUserDao extends JpaRepository<IpAddressUser,Long> {
}
