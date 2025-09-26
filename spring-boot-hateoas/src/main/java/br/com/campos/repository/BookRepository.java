package br.com.campos.repository;

import br.com.campos.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books, Long> {

}
