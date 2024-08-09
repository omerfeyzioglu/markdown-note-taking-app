package com.example.demo.services;

import com.example.demo.configuration.UserMapper;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDTO createUser(UserDTO userDTO) {


        User user = userMapper.toEntity(userDTO);

        User createdUser = userRepository.save(user);
         return userMapper.toDTO(createdUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {

        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
