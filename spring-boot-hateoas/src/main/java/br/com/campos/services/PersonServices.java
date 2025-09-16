package br.com.campos.services;

import br.com.campos.controller.PersonController;
import br.com.campos.data.dto.v1.PersonDTO;
import br.com.campos.data.dto.v2.PersonDTOV2;
import br.com.campos.exception.RequiredObjectIsNullException;
import br.com.campos.exception.ResourceNotFoundException;
import static br.com.campos.mapper.ObjectMapper.parseListObjects;
import static br.com.campos.mapper.ObjectMapper.parseObject;

import br.com.campos.mapper.custom.PersonMapper;
import br.com.campos.model.Person;
import br.com.campos.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    public List<PersonDTO> findAll() {
        logger.info("Finding all persons");
        var persons = parseListObjects(repository.findAll(), PersonDTO.class);
        persons.forEach(this::addHateoasLinks);
        return persons;
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one person!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO create(PersonDTO person) {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Person was created!");
        var entity = parseObject(person, Person.class);
        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTOV2 createV2(PersonDTOV2 person) {
        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Person was created V2!");
        var entity = converter.convertDTOToEntity(person);
        var dto = converter.convertEntityToDTO(repository.save(entity));
        addHateoasLinksV2(dto);
        return dto;
    }

    public PersonDTO update(PersonDTO person) {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Person was updated!");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this person!"));
        entity.setFirstName(person.getFirstName() );
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting one person");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
        repository.delete(entity);
    }


    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

    private void addHateoasLinksV2(PersonDTOV2 dto) {
        dto.add(linkTo(methodOn(PersonController.class).create((PersonDTOV2) null)).withSelfRel().withType("POST"));
    }
}
