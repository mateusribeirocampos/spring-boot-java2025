package com.campos.testcontainer.repositories;

import com.campos.testcontainer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}