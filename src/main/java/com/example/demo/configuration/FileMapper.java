package com.example.demo.configuration;

import com.example.demo.dto.FileDTO;
import com.example.demo.entity.File;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {

    private final ModelMapper modelMapper;

    public FileMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FileDTO toDTO(File file) {
        return modelMapper.map(file, FileDTO.class);
    }

    public File toEntity(FileDTO fileDTO) {
        return modelMapper.map(fileDTO, File.class);
    }

}
