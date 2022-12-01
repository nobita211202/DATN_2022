package org.datn.controller;

import org.datn.service.ImageService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/api/img")
@CrossOrigin("*")
@RestController
public class ControllerImg {
    @GetMapping("/get/{name}")
    public byte[] getImg(
            @PathVariable("name") String name
    ) throws IOException {
        System.out.println("image");
        return ImageService.getImage(name);
    }
}
