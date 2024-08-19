package com.example.demo.services;

import com.example.demo.dto.FileDTO;
import com.example.demo.entity.File;
import com.example.demo.entity.Note;
import com.example.demo.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final NoteService noteService;
    private final ModelMapper modelMapper;
    public FileDTO saveFile(MultipartFile multipartFile, String filePath, Long noteId) throws IOException {
        // Notu al
        Note note = noteService.getNoteById(noteId);
        File file = getFile(multipartFile, filePath, note);
        File savedFile = fileRepository.save(file);
        return modelMapper.map(savedFile, FileDTO.class);
    }

    private File getFile(MultipartFile multipartFile, String filePath, Note note) {
        // Dosya bilgilerini ayarla
        String fileName = multipartFile.getOriginalFilename();
        String fileType = determineFileType(fileName);


        File file = new File();
        file.setFileName(fileName);
        file.setFilePath(filePath); // Burada dosyanın gerçek yolu kullanılabilir
        file.setFileType(fileType);
        file.setNote(note);
        return file;
    }

    private String determineFileType(String fileName) {
        if (fileName.endsWith(".txt")) {
            return "text/plain";
        } else if (fileName.endsWith(".docx")) {
            return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + fileName);
        }
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
