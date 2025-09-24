package com.campos.testspringboot.controller.v1;

import com.campos.testspringboot.data.v1.PersonCreateDTO;
import com.campos.testspringboot.data.v1.PersonDTO;
import com.campos.testspringboot.data.v1.PersonUpdateDTO;
import com.campos.testspringboot.service.PersonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/person/v1")
public class PersonControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(PersonControllerV1.class);

    @Autowired
    private PersonService service;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<PersonDTO>> findAll() {
        logger.info("GET /api/person/v1 - Finding all people");

        List<PersonDTO> listPersonDTO = service.findAll();
        return ResponseEntity.ok().body(listPersonDTO);
    }


    @GetMapping(value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonDTO> findById(@PathVariable UUID id) {
        logger.info("GET /api/person/v1/{} - Finding person by id", id);
        PersonDTO personDTO = service.findById(id);
        return ResponseEntity.ok(personDTO);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonDTO> create(@Valid @RequestBody PersonCreateDTO createDTO) {
        logger.info("POST /api/person/v1 - creating new person");

        PersonDTO createdDTO = service.create(createDTO); // PersonCreateDTO -> Service

        // URI of resource created
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdDTO);

//      return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonDTO> update(@PathVariable UUID id, @Valid @RequestBody PersonUpdateDTO updateDTO) {
        logger.info("PUT /api/person/v1/{} - updating person by id", id);

        PersonDTO updatedDTO = service.update(id, updateDTO); // PersonUpdateDTO -> Service
        return ResponseEntity.ok(updatedDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        logger.info("DELETE /api/person/{} - deleting person", id);

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
