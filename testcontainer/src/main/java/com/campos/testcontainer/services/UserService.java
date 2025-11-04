package com.campos.testcontainer.services;

import com.campos.testcontainer.data.dto.UserResponseDto;
import com.campos.testcontainer.entities.User;
import com.campos.testcontainer.mapper.UserMapper;
import com.campos.testcontainer.repositories.UserRepository;
import com.campos.testcontainer.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserResponseDto findById(Long id) {
        logger.info("Finding one user");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return userMapper.toResponseDto(user);
    }
}
