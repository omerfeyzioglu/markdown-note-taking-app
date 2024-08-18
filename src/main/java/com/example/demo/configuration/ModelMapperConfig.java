package com.example.demo.configuration;

import com.example.demo.dto.ResponseUserDTO;
import com.example.demo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean("realMapper")
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return new ModelMapper();

    }

}
