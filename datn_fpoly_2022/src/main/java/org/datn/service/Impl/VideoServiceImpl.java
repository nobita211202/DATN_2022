package org.datn.service.Impl;

import org.datn.dao.VideoDao;
import org.datn.entity.Video;
import org.datn.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoDao vdao;
    @Override
    public List<Video> findAll() {
        return vdao.findAll();
    }

    @Override
    public Video createVideo(Video video) {
        return vdao.save(video);
    }

    @Override
    public Video updateVideo(Video video) {
        return vdao.save(video);
    }

    @Override
    public void deleteVideo(Long id) {
        vdao.deleteById(id);
    }

    @Override
    public Video findByIDVideo(Long id) {
        return vdao.findById(id).get();
    }
}
