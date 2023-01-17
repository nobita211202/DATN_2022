package org.datn.service;

import org.datn.entity.Video;

import java.util.List;
import java.util.Optional;

public interface VideoService {
    List<Video> getAll();
    List<Video> getVideosByCourse(long id);
    Optional<Video> findById(long id);
    Video save(Video video);
    void delete(long id);
    Video put(Video video);
    List<Video> search(String txtSearch);
    boolean exists(long id);
}
