package com.campos.testcontainer.services;

import com.campos.testcontainer.data.dto.UserCreateDto;
import com.campos.testcontainer.data.dto.UserResponseDto;
import com.campos.testcontainer.entities.User;
import com.campos.testcontainer.mapper.UserMapper;
import com.campos.testcontainer.repositories.UserRepository;
import com.campos.testcontainer.services.exceptions.DatabaseException;
import com.campos.testcontainer.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        logger.info("Finding one user");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return userMapper.toResponseDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        logger.info("Finding all users!");
        List<User> user = userRepository.findAll();
        for (User u : user) {
            System.out.println(u.getPassword());
        }
        return userMapper.toResponseListDto(user);
    }

    @Transactional
    public UserResponseDto create(UserCreateDto dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DatabaseException("Email was already registered!");
        }

        User user = userMapper.toEntity(dto);
        System.out.println("Created user and save in database: " + user);
        User saveUser = userRepository.save(user);
        System.out.println("Return with user filter by mapper: " + userMapper.toResponseDto(saveUser));
        return userMapper.toResponseDto(saveUser);
    }
}
