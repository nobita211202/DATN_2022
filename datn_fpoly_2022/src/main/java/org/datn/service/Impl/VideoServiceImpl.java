package org.datn.service.Impl;

import org.datn.dao.VideoDao;
import org.datn.entity.Video;
import org.datn.service.VideoService;
import org.datn.utils.Base.Bases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoDao videoDao;
    @Autowired
    Bases<Video> bases;

    @Override
    public List<Video> getAll() {
        return null;
    }

    @Override
    public List<Video> getVideosByCourse(long idCourse) {
        return videoDao.getVideoByCourse(idCourse);
    }

    @Override
    public Optional<Video> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Video save(Video video) {
        return videoDao.save(bases.getBase(video,Bases.CREATE));
    }

    @Override
    public void delete(long id) {
        videoDao.deleteById(id);
    }

    @Override
    public Video put(Video video) {
        return videoDao.save(bases.getBase(video,Bases.UPDATE));
    }

    @Override
    public List<Video> search(String txtSearch) {
        return null;
    }

    @Override
    public boolean exists(long id) {
        return videoDao.existsById(id);
    }
}
