package org.datn.service;

import org.datn.entity.Contact;
import org.datn.entity.ContactSubject;

import java.util.List;
import java.util.Optional;

public interface ContactSubjectService {
    List<ContactSubject> getAll();
    Optional<ContactSubject> findById(long id);
    ContactSubject save(ContactSubject contactSubject);
    void delete(long id);
    ContactSubject put(ContactSubject contactSubject);
    boolean exists(long contactSubject);
}
