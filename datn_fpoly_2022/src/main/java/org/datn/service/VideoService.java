package org.datn.service;

import org.datn.entity.Video;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface VideoService {
    List<Video> findAll();

    Video createVideo(Video video);

    Video updateVideo(Video video);

    void deleteVideo(Long id);

    Video findByIDVideo(Long id);
}
