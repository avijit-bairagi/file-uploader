package com.example.fileuploader.controller;

import com.example.fileuploader.common.Constants;
import com.example.fileuploader.model.MqTask;
import com.example.fileuploader.service.FileService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller()
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/image")
    public ResponseEntity<String> imageUpload(@RequestParam("file") MultipartFile file) throws IOException {

        fileService.saveImage(file);

        return ResponseEntity.ok("Image uploaded successfully");

    }

    @PostMapping("/images")
    public ResponseEntity<String> imageUploads(@RequestParam("files") MultipartFile[] files) throws IOException {

        fileService.saveImages(files);

        return ResponseEntity.ok("Images uploaded successfully");

    }
}
