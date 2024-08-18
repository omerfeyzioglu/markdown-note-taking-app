package com.example.demo.controllers;


import com.example.demo.configuration.FileMapper;
import com.example.demo.dto.ResponseUserDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ObjectMapper objectMapper;

    
    @PostMapping("")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO ) {
       userService.createUser(userDTO);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResponseUserDTO>> getAllUsers() {
        List<ResponseUserDTO> response  = userService.getAllUsers();
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")      //reponse entity type did not defined to be given errorMessage!
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
      try {
          ResponseUserDTO responseUserDTO = userService.getUserById(id);
          return ResponseEntity.ok(responseUserDTO);
      }catch (Exception e) {
          String errorMessage = "There is no such user has this id!";
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
      }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
