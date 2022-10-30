package org.datn.dao;

import org.datn.entity.ContactSubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactSubjectDao extends JpaRepository<ContactSubject,Long> {
}
