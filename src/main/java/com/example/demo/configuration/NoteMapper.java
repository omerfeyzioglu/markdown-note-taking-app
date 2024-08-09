package com.example.demo.configuration;

import com.example.demo.dto.NoteDTO;
import com.example.demo.entity.Note;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    private final ModelMapper modelMapper;

    public NoteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NoteDTO toDTO(Note note) {
        return modelMapper.map(note, NoteDTO.class);
    }

    public Note toEntity(NoteDTO noteDTO) {
        return modelMapper.map(noteDTO, Note.class);
    }

}
