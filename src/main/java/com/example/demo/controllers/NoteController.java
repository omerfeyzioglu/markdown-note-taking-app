package com.example.demo.controllers;


import com.example.demo.dto.NoteDTO;
import com.example.demo.dto.NoteResponseDTO;
import com.example.demo.entity.Note;
import com.example.demo.entity.User;
import com.example.demo.services.NoteService;
import com.example.demo.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<NoteResponseDTO> addNote(@RequestBody NoteDTO noteDTO ) {
        NoteResponseDTO noteResponseDTO = noteService.createNote(noteDTO);
        return new ResponseEntity<>(noteResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Note note = noteService.getNoteById(id);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Note>> getNotesByUserId(@PathVariable Long userId) {
        List<Note> notes = noteService.getNotesByUserId(userId);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable Long id) {

            String asd = noteService.deleteNote(id);

           return new ResponseEntity<>(asd,HttpStatus.INTERNAL_SERVER_ERROR);


    }
}
