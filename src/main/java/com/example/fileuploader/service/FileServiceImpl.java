package com.example.fileuploader.service;

import com.example.fileuploader.common.Constants;
import com.example.fileuploader.entity.FileEntity;
import com.example.fileuploader.model.MqTask;
import com.example.fileuploader.repository.FileRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void saveImage(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileEntity fileEntity = new FileEntity(fileName, file.getContentType(), file.getBytes());
        fileEntity = fileRepository.save(fileEntity);
        publishToRabbitMq(fileEntity.getId());

    }

    @Override
    public void saveImages(MultipartFile[] files) throws IOException {

        for(MultipartFile file : files) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            FileEntity fileEntity = new FileEntity(fileName, file.getContentType(), file.getBytes());
            fileEntity = fileRepository.save(fileEntity);
            publishToRabbitMq(fileEntity.getId());
        }
    }

    @Override
    public void save(FileEntity file) {
        fileRepository.save(file);
    }

    @Override
    public FileEntity findById(String id) {
        return fileRepository.findById(id)
                .orElseThrow();
    }

    private void publishToRabbitMq(String id) {

        rabbitTemplate.convertAndSend(Constants.IMAGE_PROCESSING_QUEUE,
                new MqTask(id, "Image Processing Task."));
    }
}