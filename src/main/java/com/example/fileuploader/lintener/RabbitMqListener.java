package com.example.fileuploader.lintener;

import com.example.fileuploader.common.Constants;
import com.example.fileuploader.model.MqTask;
import com.example.fileuploader.model.OutputMessage;
import com.example.fileuploader.service.ImageProcessingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class RabbitMqListener {

    @Autowired
    private ImageProcessingService imageProcessingService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = Constants.IMAGE_PROCESSING_QUEUE)
    public void listen(MqTask task) throws IOException {
        System.out.println("Message read from myQueue : " + task);
        imageProcessingService.process(task);

        publishToWebSocket();
    }

    private void publishToWebSocket() {

        simpMessagingTemplate.convertAndSend("/topic/image-resize",
                new OutputMessage("Image resized Successfully.", new SimpleDateFormat("HH:mm").format(new Date())));
    }
}
