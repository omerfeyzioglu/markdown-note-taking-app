package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoteResponseDTO {

    private String username;

    private String title;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
