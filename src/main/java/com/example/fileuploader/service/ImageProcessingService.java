package com.example.fileuploader.service;

import com.example.fileuploader.model.MqTask;

import java.io.IOException;

public interface ImageProcessingService {

    void process(MqTask task) throws IOException;
}
