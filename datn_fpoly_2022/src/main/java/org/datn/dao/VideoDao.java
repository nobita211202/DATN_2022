package org.datn.dao;

import org.datn.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoDao extends JpaRepository<Video,Long> {
}
