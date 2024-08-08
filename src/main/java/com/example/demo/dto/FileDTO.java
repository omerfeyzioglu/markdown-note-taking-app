package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileDTO {

    private Long id;

    private String fileName;

    private String fileType;

    private String filePath;

    private LocalDateTime uploadedAt;


}
