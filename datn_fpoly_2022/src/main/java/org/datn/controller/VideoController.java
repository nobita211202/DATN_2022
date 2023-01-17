package org.datn.controller;

import org.datn.entity.Video;
import org.datn.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/video")
@CrossOrigin("*")
public class VideoController {
    @Autowired
    VideoService videoService;

    @GetMapping("/getByCourseId/{idCourse}")
    public ResponseEntity getByCourseId(
            @PathVariable("idCourse") long idCourse
    ){
        return ResponseEntity.ok(videoService.getVideosByCourse(idCourse));
    }

    @PostMapping("/add")
    public ResponseEntity addVideo(
            @RequestBody Video video
    ){
        videoService.save(video);
        return ResponseEntity.ok(video);
    }
    @DeleteMapping("/delete/{idVideo}")
    public void delete(
            @PathVariable("idVideo") long idVideo
    ){
        videoService.delete(idVideo);
    }
    @PutMapping("/update")
    public ResponseEntity update(
            @RequestBody() Video video
    ){
        return ResponseEntity.ok(videoService.put(video));
    }
}
