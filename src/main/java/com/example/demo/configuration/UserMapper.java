package com.example.demo.configuration;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        System.out.println("Converted UserDTO: " + userDTO);
        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        System.out.println("Converted User: " + user);
        return user;
    }


    public List<UserDTO> toDTO(List<User> users) {
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<User> toEntity(List<UserDTO> userDTOs) {
        return userDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
