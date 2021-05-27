package com.example.fileuploader.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] imageData;

    public FileEntity(String fileName, String contentType, byte[] imageData) {

        this.fileName = fileName;
        this.fileType = contentType;
        this.imageData = imageData;
    }
}