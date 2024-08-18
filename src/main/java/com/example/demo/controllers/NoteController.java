package com.example.demo.controllers;


import com.example.demo.dto.NoteDTO;
import com.example.demo.entity.Note;
import com.example.demo.entity.User;
import com.example.demo.services.NoteService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

//    @PostMapping
//    public ResponseEntity<Note> addNote(@RequestBody NoteDTO noteDTO ) {
//
//        Note note = new Note();
//        note.setTitle(noteDTO.getTitle());
//        note.setContent(noteDTO.getContent());
//
//        User user = userService.getUserById(noteDTO.getUserId());
//        note.setUser(user);
//
//        Note createdNote = noteService.createNote(note);
//
//        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
//
//
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
//        Note note = noteService.getNoteById(id);
//        return new ResponseEntity<>(note, HttpStatus.OK);
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<Note>> getNotesByUserId(@PathVariable Long userId) {
//        List<Note> notes = noteService.getNotesByUserId(userId);
//        return new ResponseEntity<>(notes, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
//        noteService.deleteNote(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
