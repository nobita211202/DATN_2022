package org.datn.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageService<T> {
    final static String pathImg="images/";
    public static String saveImage(MultipartFile image){
        Path path= Paths.get(pathImg);
        String name="default.png";

            try {
                InputStream inputStream= image.getInputStream();
                name= System.currentTimeMillis()+image.getOriginalFilename();
                Files.copy(inputStream,path.resolve(name),StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return name;
    }

    static public byte[] getImage(String name) throws IOException {
        try {
            File file = new File(pathImg+"/"+name);
            byte[] bytes = Files.readAllBytes(file.toPath());
            return bytes;
        }catch (Exception e){
            File file = new File(pathImg+"/default.png");
            byte[] bytes = Files.readAllBytes(file.toPath());
            return bytes;
        }
    }



}
