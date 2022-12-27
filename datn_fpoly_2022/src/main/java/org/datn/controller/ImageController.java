package org.datn.controller;

import org.datn.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*")
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
