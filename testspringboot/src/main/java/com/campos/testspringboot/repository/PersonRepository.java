package com.campos.testspringboot.repository;

import com.campos.testspringboot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
