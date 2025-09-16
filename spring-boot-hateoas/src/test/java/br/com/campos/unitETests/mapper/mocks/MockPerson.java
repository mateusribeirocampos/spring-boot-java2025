package br.com.campos.unitETests.mapper.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.campos.data.dto.v2.PersonDTOV2;
import br.com.campos.model.Person;
import br.com.campos.data.dto.v1.PersonDTO;

public class MockPerson {


    public Person mockEntity() {
        return mockEntity(0);
    }
    
    public PersonDTO mockDTO() {
        return mockDTO(0);
    }
    
    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonDTO> mockDTOList() {
        List<PersonDTO> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockDTO(i));
        }
        return persons;
    }
    
    public Person mockEntity(Integer number) {
        Person person = new Person();
        person.setAddress("Address Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

    public PersonDTO mockDTO(Integer number) {
        PersonDTO person = new PersonDTO();
        person.setAddress("Address Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

    public PersonDTOV2 mockDTOV2(Integer number) {
        PersonDTOV2 personv2 = new PersonDTOV2();
        personv2.setAddress("Address Test" + number);
        personv2.setFirstName("First Name Test" + number);
        personv2.setGender(((number % 2)==0) ? "Male" : "Female");
        personv2.setId(number.longValue());
        personv2.setLastName("Last Name Test" + number);
        return personv2;
    }

}