package com.campos.testspringboot.controller.v1;

import com.campos.testspringboot.data.v1.PersonDTO;
import com.campos.testspringboot.model.Person;
import com.campos.testspringboot.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/person/v1")
public class PersonControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(PersonControllerV1.class);

    @Autowired
    private PersonService service;

    @GetMapping(value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonDTO> findById(@PathVariable UUID id) {
        logger.info("GET /api/person/v1/{} - Finding person by id", id);
        PersonDTO personDTO = service.findById(id);
        return ResponseEntity.ok(personDTO);
    }


}
