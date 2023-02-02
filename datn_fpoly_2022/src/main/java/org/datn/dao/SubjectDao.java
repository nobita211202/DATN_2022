package org.datn.dao;

import org.datn.entity.ContactSubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectDao extends JpaRepository<ContactSubject,Long> {

}
