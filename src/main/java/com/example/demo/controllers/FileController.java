package com.example.demo.controllers;

import com.example.demo.dto.FileDTO;
import com.example.demo.entity.File;
import com.example.demo.entity.Note;
import com.example.demo.services.FileService;
import com.example.demo.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<FileDTO> uploadFile(@RequestParam("file") MultipartFile multipartFile,
                                              @RequestParam("filePath") String filePath,
                                              @RequestParam("noteId") Long noteId) throws IOException {
     try {
         FileDTO fileDTO = fileService.saveFile(multipartFile, filePath, noteId);
         return new ResponseEntity<>(fileDTO, HttpStatus.CREATED);
     }catch (Exception e){
         return new ResponseEntity<>(null,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
     }


    }
    @GetMapping("/{id}")
    public ResponseEntity<FileDTO> getFileById(@PathVariable Long id) {
        FileDTO fileDTO = fileService.getFileById(id);

        return new ResponseEntity<>(fileDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        try {
            fileService.deleteFile(id);
            return new ResponseEntity<>("File successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("File not found or could not be deleted", HttpStatus.NOT_FOUND);
        }
    }
}