package org.datn.utils.Base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.datn.entity.User;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class EntityAndImage {
    private MultipartFile file;
    private String json;

}
