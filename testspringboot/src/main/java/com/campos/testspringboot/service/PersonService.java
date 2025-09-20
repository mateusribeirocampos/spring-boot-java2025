package com.campos.testspringboot.service;

import com.campos.testspringboot.data.v1.PersonDTO;
import com.campos.testspringboot.mapper.ObjectMapper;
import com.campos.testspringboot.model.Person;
import com.campos.testspringboot.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    PersonRepository repository;

   public PersonDTO findById(UUID id) {
       logger.info("Finding person with id: {}", id);

       if (id == null) {
           throw new IllegalStateException("id cannot be null");
       }

       Person entity = repository.findById(id)
               .orElseThrow(() -> new IllegalStateException("no person found with id" + id));

       PersonDTO dto = ObjectMapper.parseObject(entity, PersonDTO.class);
        return dto;
   }

}
