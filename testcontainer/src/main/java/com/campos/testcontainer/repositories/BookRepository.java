package com.campos.testcontainer.repositories;

import com.campos.testcontainer.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
