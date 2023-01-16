package org.datn.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageService {
    private final String path="../images/";
    public String saveImage(MultipartFile image){
        String name="default.png";
        if(image==null) return name;
        Path path= Paths.get(this.path);
        File dir = new File(this.path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String s = System.currentTimeMillis()+image.getOriginalFilename();
        name=Integer.toHexString(s.hashCode())+s.substring(s.lastIndexOf("."));
        try {
            InputStream inputStream = image.getInputStream();
            Files.copy(inputStream,path.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            System.out.println(path+name);
            return name;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public byte[] getImage(String name) throws IOException {
        try {
            File file = new File(this.path+name);
            byte[] bytes = Files.readAllBytes(file.toPath());
            return bytes;
        }catch (IOException e){
            System.out.println("err :"+name);
            File file = new File(path+"default.jpg");
            byte[] bytes = Files.readAllBytes(file.toPath());
            return bytes;
        }
    }



}
