package com.example.demo.services;

import com.example.demo.dto.NoteDTO;
import com.example.demo.dto.NoteResponseDTO;
import com.example.demo.entity.Note;
import com.example.demo.entity.User;
import com.example.demo.repositories.NoteRepository;
import com.example.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final NoteRepository noteRepository;

    public NoteResponseDTO createNote(NoteDTO noteDTO) {
        Optional<User> user = userService.getUserByNoteDTO(noteDTO);
        noteDTO.setUserId(user.get().getId());
        Note note = noteRepository.save(modelMapper.map(noteDTO, Note.class));
       NoteResponseDTO noteResponseDTO = modelMapper.map(note, NoteResponseDTO.class);
       noteResponseDTO.setUsername(user.get().getUsername());   // username has to be set.
        return noteResponseDTO;


    }

    @Transactional   /*it must be a transtactional operation because of we defined content field as @Lob and it contains big data.*/
    public List<Note> getNotesByUserId(Long userId) {

        List<Note> noteList  = noteRepository.findByUserId(userId);

        if (noteList == null || noteList.isEmpty()) {
            return Collections.emptyList();
        }

        return noteList;
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }

    public String deleteNote(Long id) {
        try {
            if (noteRepository.existsById(id)) {
                noteRepository.deleteById(id);
                return "Note deleted";
            } else {
                return "Note not found";
            }
        } catch (Exception e) {
            return "Could not delete note";
        }

    }


}
