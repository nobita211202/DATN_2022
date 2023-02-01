package org.datn.dao;

import org.datn.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactDao extends JpaRepository<Contact,Long> {
    List<Contact> getContactByStatus(short status);
}
