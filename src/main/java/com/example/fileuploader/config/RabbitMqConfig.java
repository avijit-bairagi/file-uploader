package com.example.fileuploader.config;

import com.example.fileuploader.common.Constants;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue imageProcessingQueue() {
        return new Queue(Constants.IMAGE_PROCESSING_QUEUE, false);
    }
}
