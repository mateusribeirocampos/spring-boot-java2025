package com.campos.testcontainer.controllers;

import com.campos.testcontainer.data.dto.UserCreateDto;
import com.campos.testcontainer.data.dto.UserResponseDto;
import com.campos.testcontainer.data.dto.UserUpdateDto;
import com.campos.testcontainer.entities.User;
import com.campos.testcontainer.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users/v1")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        logger.info("GET /api/users/v1/${id} - Finding one user{}", id);
        UserResponseDto userDto = userService.findById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponseDto>> findAll() {
        logger.info("GET /api/users/v1 - Finding users");
        List<UserResponseDto> userListDto = userService.findAll();
        return ResponseEntity.ok().body(userListDto);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateDto dto) {
        logger.info("POST /api/users/v1 - creating user");
        UserResponseDto created = userService.create(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody UserUpdateDto dto) {
        logger.info("PUT /api/users/v1/${di}{}user: {}", id, dto.getFirstName());
        UserResponseDto updated = userService.update(id, dto);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        logger.info("DELETE /api/users/v1/${id}{}", id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
