package com.campos.testcontainer.controllers;

import com.campos.testcontainer.data.dto.UserResponseDto;
import com.campos.testcontainer.entities.User;
import com.campos.testcontainer.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users/v1")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        logger.info("GET /api/users/v1 - Finding one user");
        UserResponseDto userDto = userService.findById(id);
        return ResponseEntity.ok(userDto);
    }

}
