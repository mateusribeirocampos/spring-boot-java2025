package br.com.campos.services;

import br.com.campos.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());


    public List<Person> findAll() {
        logger.info("Finding all persons");
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 10; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("First name: " + i);
        person.setLastName("Last name: " + i);
        person.setAddress("Address: " + i);
        person.setGender("Male");
        return person;
    }

    public Person findById(String id) {
        logger.info("Finding one person!");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Mateus");
        person.setLastName("Ribeiro de Campos");
        person.setAddress("Ouro Fino - Minas Gerais");
        person.setGender("Male");

        return person;
    }

    public Person create(Person person) {
        logger.info("Person was created!");
        return person;
    }

    public Person update(Person person) {
        logger.info("Person was updated!");
        return person;
    }

    public void delete(String id) {
        logger.info("Deleting one person");
    }

}
