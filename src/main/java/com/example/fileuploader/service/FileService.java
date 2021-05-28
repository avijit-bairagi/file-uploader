package com.example.fileuploader.service;

import com.example.fileuploader.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    void saveImage(MultipartFile file) throws IOException;

    void saveImages(MultipartFile[] files) throws IOException;

    void save(FileEntity file);

    FileEntity findById(String id);

}