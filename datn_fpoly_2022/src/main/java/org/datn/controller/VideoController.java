package org.datn.controller;

import org.datn.dao.VideoDao;
import org.datn.entity.Cours;
import org.datn.entity.Video;
import org.datn.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController("/api/video")
public class VideoController {
    @Autowired
    VideoService videoService;

    @GetMapping("/listVideo")
    public List<Video> getAll(){
        return videoService.findAll();
    }

    @PostMapping("/createVideo")
    public Video createVideo(@RequestBody Video video ){
        return videoService.createVideo(video);
    }
    @PutMapping("/updateVideo/{id}")
    public Video updateVideo(@PathVariable("id") Long id ,@RequestBody Video video){
        return videoService.updateVideo(video);
    }
    @DeleteMapping("/deleteVideo/{id}")
    public void deleteVideo(@PathVariable("id") Long id){
        videoService.deleteVideo(id);
    }
    @GetMapping("/findByIDVideo/{id}")
    public Video findByIDVideo(@PathVariable("id") Long id){
        return videoService.findByIDVideo(id);
    }
}
