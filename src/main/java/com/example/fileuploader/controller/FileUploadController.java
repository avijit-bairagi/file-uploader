package com.example.fileuploader.controller;

import com.example.fileuploader.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller()
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @PostMapping("/image")
    public ResponseEntity<String> imageUpload(@RequestParam("file") MultipartFile file) throws IOException {

        fileService.save(file);

        return ResponseEntity.ok("Image uploaded successfully");

    }
}
