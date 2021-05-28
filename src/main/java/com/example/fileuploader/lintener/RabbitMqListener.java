package com.example.fileuploader.lintener;

import com.example.fileuploader.common.Constants;
import com.example.fileuploader.model.MqTask;
import com.example.fileuploader.service.ImageProcessingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RabbitMqListener {

    @Autowired
    private ImageProcessingService imageProcessingService;

    @RabbitListener(queues = Constants.IMAGE_PROCESSING_QUEUE)
    public void listen(MqTask task) throws IOException {
        System.out.println("Message read from myQueue : " + task);
        imageProcessingService.process(task);
    }
}
