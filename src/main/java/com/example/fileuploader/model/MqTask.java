package com.example.fileuploader.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MqTask implements Serializable {

    private String id;

    private String message;
}
