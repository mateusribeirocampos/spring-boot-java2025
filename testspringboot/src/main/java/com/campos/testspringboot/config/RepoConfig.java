package com.campos.testspringboot.config;

import com.campos.testspringboot.model.Person;
import com.campos.testspringboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@Configuration
@Profile("dev")
public class RepoConfig implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() > 0) {
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Person person1 = new Person(null, "Airton", "Senna", "airton.senna@email", "12996235874", "123456879", sdf.parse("21/03/1960"), "Rua example, 12354", "SP", "M", LocalDateTime.now(), LocalDateTime.now());
        Person person2 = new Person(null, "Silvio", "Santos", "silvio.sanots@email", "12996235874", "123456879", sdf.parse("12/12/1930"), "Rua example, 12354", "SP", "M", LocalDateTime.now(), LocalDateTime.now());

        repository.saveAll(Arrays.asList(person1, person2));
    }
}
