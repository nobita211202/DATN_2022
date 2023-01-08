package org.datn.dao;

import org.datn.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDao extends JpaRepository<Contact,Long> {

}
