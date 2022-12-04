package org.datn.controller;

import org.datn.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/api/img")
@CrossOrigin("*")
@RestController
public class ControllerImg {
    @Autowired
    ImageService service;
    @GetMapping("/get/{name}")
    public byte[] getImg(
            @PathVariable("name") String name
    ) throws IOException {
        System.out.println("image");
        return service.getImage(name);
    }
}
