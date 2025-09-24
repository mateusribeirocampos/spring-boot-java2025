package com.campos.testspringboot.service;

import com.campos.testspringboot.data.v1.PersonCreateDTO;
import com.campos.testspringboot.data.v1.PersonDTO;
import com.campos.testspringboot.data.v1.PersonUpdateDTO;
import com.campos.testspringboot.exception.RequiredObjectIsNullException;
import com.campos.testspringboot.exception.ResourceNotFoundException;
import com.campos.testspringboot.mapper.ObjectMapper;
import com.campos.testspringboot.model.Person;
import com.campos.testspringboot.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {

    private Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {
        logger.info("Finding all people");
        return ObjectMapper.parseListObjects(repository.findAll(), PersonDTO.class);
    }

   public PersonDTO findById(UUID id) {
       logger.info("Finding person with id: {}", id);
       if (id == null) {
           throw new RequiredObjectIsNullException("ID cannot be null");
       }
       Person entity = repository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("no person found with id: " + id));
       return ObjectMapper.parseObject(entity, PersonDTO.class);
   }

   public PersonDTO create(PersonCreateDTO createDTO) {
       logger.info("Creating new person");

        // Validation
       if (createDTO == null) {
           throw new RequiredObjectIsNullException("PersonCreateDTO cannot be null");
       }
       // PersonCreateDTO -> Person Entity
       Person entity = ObjectMapper.parseObject(createDTO, Person.class);
       // Save in the database (ID and createdAt automatically generated)
       Person savedEntity = repository.save(entity);
       // Person entity -> PersonDTO to response
       return ObjectMapper.parseObject(savedEntity, PersonDTO.class);
   }

   public PersonDTO update(UUID id, PersonUpdateDTO updateDTO) {
       logger.info("Updating person with id: {}", id);
        if (id == null) throw new RequiredObjectIsNullException("ID cannot be null");
        if (updateDTO == null) throw new RequiredObjectIsNullException("PersonUpdateDTO cannot be null");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No person found with id: " + id));

        if (updateDTO.getFirstName() != null) entity.setFirstName(updateDTO.getFirstName());
        if (updateDTO.getLastName() != null) entity.setLastName(updateDTO.getLastName());
        if (updateDTO.getEmail() != null) entity.setEmail(updateDTO.getEmail());
        if (updateDTO.getPhoneNumber() != null) entity.setPhoneNumber(updateDTO.getPhoneNumber());
        if (updateDTO.getPassword() != null) entity.setPassword(updateDTO.getPassword());
        if (updateDTO.getBirthDate() != null) entity.setBirthDate(updateDTO.getBirthDate());
        if (updateDTO.getAddress() != null) entity.setAddress(updateDTO.getAddress());
        if (updateDTO.getState() != null) entity.setState(updateDTO.getState());
        if (updateDTO.getGender() != null) entity.setGender(updateDTO.getGender());

        Person savedEntity = repository.save(entity);
        return ObjectMapper.parseObject(savedEntity, PersonDTO.class);
   }

   public void delete(UUID id) {
        logger.info("Deleting person with id: {}", id);

        if (id == null) throw new RequiredObjectIsNullException("ID cannot be null");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No person found with id: " + id));

        repository.delete(entity);
   }

}
