package br.com.campos.controller;

import br.com.campos.controller.docs.PersonControllerDocs;
import br.com.campos.data.dto.v1.PersonDTO;
import br.com.campos.data.dto.v2.PersonDTOV2;
import br.com.campos.services.PersonServices;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "Person", description = "Endpoints for managing person")
public class PersonController implements PersonControllerDocs {

    @Autowired
    private PersonServices service;

    @GetMapping(value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )

    @Override
    public PersonDTO findById(@Parameter(description = "The Id of the person to find.") @PathVariable("id") Long id) {
        var person = service.findById(id);
        person.setBirthDay(new Date());
        person.setPhoneNumber("+55 31 992359874");
        person.setSensitiveData("Foo bar");
        return service.findById(id);
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}
    )

    @Override
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}
    )

    @Override
    public PersonDTO create(@RequestBody PersonDTO person) {
       return service.create(person);
    }

    @PostMapping(value = "/v2",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}
    )

    @Override
    public PersonDTOV2 create(@RequestBody PersonDTOV2 person) {
       return service.createV2(person);
    }

    @PutMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}
    )

    @Override
    public PersonDTO update(@RequestBody PersonDTO person) {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")

    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}