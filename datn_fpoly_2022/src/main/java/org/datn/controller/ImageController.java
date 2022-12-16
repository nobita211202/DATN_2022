package org.datn.controller;

import org.datn.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Autowired
    ImageService service;

    @GetMapping("/get/{name}")
    public byte[] getImg(
            @PathVariable("name") String name
    ) throws IOException {
        System.out.println(name);
        return service.getImage(name);
    }
}
