package com.example.demo.services;


//import com.example.demo.configuration.UserMapper;
import com.example.demo.dto.NoteDTO;
import com.example.demo.dto.ResponseUserDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

//    private final UserMapper userMapper;

     @Qualifier("realMapper")
    private final ModelMapper modelMapper;

    public User createUser(UserDTO userDTO) {
        User user = getUser(userDTO);
        userRepository.save(user);
         return user;
    }
    private User getUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    public List<ResponseUserDTO> getAllUsers() {
        List<ResponseUserDTO> responseUserDTO = getResponseUserDTOS();
        log.info("Retrieved users: {}", responseUserDTO);
        return responseUserDTO;
    }
    private List<ResponseUserDTO> getResponseUserDTOS() {
        List<ResponseUserDTO> responseUserDTO = userRepository.findAll().stream().map((element) -> modelMapper.map(element, ResponseUserDTO.class)).collect(Collectors.toList());
        return responseUserDTO;
    }

    public ResponseUserDTO getUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        ResponseUserDTO responseUserDTO = getResponseUserDTO(user);
        return responseUserDTO;

    }

    public Optional<User> getUserByNoteDTO(NoteDTO noteDTO) {
        Optional<User> user = userRepository.findById(noteDTO.getUserId());

        return user;
    }


    private ResponseUserDTO getResponseUserDTO(User user) {
        ResponseUserDTO responseUserDTO = modelMapper.map(user, ResponseUserDTO.class);
        return responseUserDTO;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
