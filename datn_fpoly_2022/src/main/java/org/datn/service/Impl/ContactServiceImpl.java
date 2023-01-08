package org.datn.service.Impl;

import org.datn.dao.ContactDao;
import org.datn.dao.ContactSubjectDao;
import org.datn.entity.Contact;
import org.datn.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactDao contactDao;
    @Autowired
    ContactSubjectDao subjectDao;
    @Override
    public List<Contact> getAll() {
        return contactDao.findAll();
    }

    @Override
    public Optional<Contact> findById(long id) {
        return contactDao.findById(id);
    }

    @Override
    public Contact save(Contact contact) {

        return contactDao.save(contact);
    }

    @Override
    public void delete(long id) {
        contactDao.deleteById(id);
    }

    @Override
    public Contact put(Contact contact) {
        return contactDao.save(contact);
    }

    @Override
    public boolean exists(long contact) {
        return false;
    }
}
