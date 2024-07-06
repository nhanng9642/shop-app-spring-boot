package com.example.demo.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public String uploadFile(MultipartFile file) {
        try {
            Map params = ObjectUtils.asMap(
                    "use_filename", true,
                    "unique_filename", true
            );

            Map data = cloudinary.uploader().upload(file.getBytes(), params);
            return (String)data.get("url");
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String uploadFile(File file) {
        try {
            Map params = ObjectUtils.asMap(
                    "use_filename", true,
                    "unique_filename", true
            );

            Map data = cloudinary.uploader().upload(file, params);
            return (String) data.get("url");
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
