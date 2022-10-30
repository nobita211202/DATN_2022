package org.datn.dao;

import org.datn.entity.MailOutQueue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailOutQueueDao extends JpaRepository<MailOutQueue,Long> {
}
