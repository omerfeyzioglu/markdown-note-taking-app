package com.example.demo.services;

import com.example.demo.entity.File;
import com.example.demo.entity.Note;
import com.example.demo.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public File saveFile(String fileName, String filePath, Note note) throws IOException {

        Path path = Paths.get(filePath);
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            throw new IllegalArgumentException("File path is not valid.");
        }


        String fileExtension = getFileExtension(fileName);
        if (!isValidFileType(fileExtension)) {
            throw new IllegalArgumentException("Invalid file type. Only .txt and .docx files are allowed.");
        }


        File file = new File();
        file.setFileName(fileName);
        file.setFilePath(filePath);
        file.setUploadedAt(java.time.LocalDateTime.now());
        file.setNote(note);

        return fileRepository.save(file);
    }

    private String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return "";
        }
        return fileName.substring(lastIndexOfDot).toLowerCase();
    }

    private boolean isValidFileType(String extension) {
        return ".txt".equals(extension) || ".docx".equals(extension);
    }

    public File getFileById(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }
}
