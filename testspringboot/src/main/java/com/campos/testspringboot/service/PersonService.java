package com.campos.testspringboot.service;

import com.campos.testspringboot.data.v1.PersonDTO;
import com.campos.testspringboot.exception.RequiredObjectIsNullException;
import com.campos.testspringboot.exception.ResourceNotFoundException;
import com.campos.testspringboot.mapper.ObjectMapper;
import com.campos.testspringboot.model.Person;
import com.campos.testspringboot.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
               .orElseThrow(() -> new ResourceNotFoundException("no person found with id" + id));

       return ObjectMapper.parseObject(entity, PersonDTO.class);
   }

}
