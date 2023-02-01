package org.datn.service;

import org.datn.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    List<Contact> getAll();
    Optional<Contact> findById(long id);
    Contact save(Contact contact);
    void delete(long id);
    Contact put(Contact contact);
    boolean exists(long contact);
    List<Contact> getByStatus(short status);
}
