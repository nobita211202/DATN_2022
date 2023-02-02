package org.datn.service.Impl;

import org.datn.dao.ContactDao;
import org.datn.dao.ContactSubjectDao;
import org.datn.entity.Contact;
import org.datn.entity.ContactSubject;
import org.datn.service.ContactService;
import org.datn.service.ContactSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactSubjectServiceImpl implements ContactSubjectService {

    @Autowired
    ContactSubjectDao subjectDao;

    @Override
    public List<ContactSubject> getAll() {
        return subjectDao.findAll();
    }

    @Override
    public Optional<ContactSubject> findById(long id) {
        return Optional.empty();
    }

    @Override
    public ContactSubject save(ContactSubject contactSubject) {
        return subjectDao.save(contactSubject);
    }

    @Override
    public void delete(long id) {
        subjectDao.deleteById(id);
    }

    @Override
    public ContactSubject put(ContactSubject contactSubject) {
        return subjectDao.save(contactSubject);
    }

    @Override
    public boolean exists(long contactSubject) {
        return false;
    }
}
