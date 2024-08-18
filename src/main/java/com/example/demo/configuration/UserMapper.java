//package com.example.demo.configuration;
//
//import com.example.demo.dto.ResponseUserDTO;
//import com.example.demo.entity.User;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserMapper {
//
//    private final ModelMapper modelMapper;
//
//    @Autowired
//    public UserMapper(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }
//
//    public ResponseUserDTO toDto(User user) {
//        modelMapper.createTypeMap(User.class, ResponseUserDTO.class)
//                .addMapping(User::getUsername, ResponseUserDTO::setUsername)       //you can set that the field you want to see in responseDTO list after added into DTO.
//                .addMapping(User::getCreatedAt, ResponseUserDTO::setCreatedAt);
////                .addMapping(User::getEmail, ResponseUserDTO::setEmail)
//
//        return modelMapper.map(user, ResponseUserDTO.class);
//    }
//
//    public User toEntity(ResponseUserDTO userDTO) {
//        return modelMapper.map(userDTO, User.class);
//    }
//}
