package com.example.demo.controllers;


import com.example.demo.dto.ResponseUserDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /*private final ModelMapper modelMapper;
    private final Gson gson;
    private final ObjectMapper objectMapper;*/


    @PostMapping("")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResponseUserDTO>> getAllUsers() {
        List<ResponseUserDTO> response = userService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")      //reponse entity type did not defined to be given errorMessage!
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            ResponseUserDTO responseUserDTO = userService.getUserById(id);
            return ResponseEntity.ok(responseUserDTO);
        } catch (Exception e) {
            String errorMessage = "There is no such user has this id!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            String result = userService.deleteUser(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
