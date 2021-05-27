package com.example.fileuploader.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    void save(MultipartFile file) throws IOException;

}