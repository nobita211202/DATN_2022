package org.datn.DAO;

import org.datn.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDao extends JpaRepository<Contact,Long> {
}
