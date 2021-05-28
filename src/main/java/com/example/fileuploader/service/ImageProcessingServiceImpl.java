package com.example.fileuploader.service;

import com.example.fileuploader.entity.FileEntity;
import com.example.fileuploader.model.MqTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Service
public class ImageProcessingServiceImpl implements ImageProcessingService {

    @Autowired
    FileService fileService;

    @Override
    public void process(MqTask task) throws IOException {

        FileEntity fileEntity = fileService.findById(task.getId());

        byte[] thumbnailData = resizeImage(fileEntity);
        fileEntity.setThumbnailData(thumbnailData);

        fileService.save(fileEntity);
    }

    private byte[] resizeImage(FileEntity fileEntity) throws IOException {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileEntity.getImageData());
        BufferedImage originalImage = ImageIO.read(byteArrayInputStream);

        BufferedImage resizedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, 200, 200, null);
        graphics2D.dispose();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
