package br.com.campos.services;

import br.com.campos.controller.BookController;
import br.com.campos.data.dto.v1.BookDTO;
import br.com.campos.exception.RequiredObjectIsNullException;
import br.com.campos.exception.ResourceNotFoundException;
import br.com.campos.model.Books;
import br.com.campos.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.campos.mapper.ObjectMapper.parseListObjects;
import static br.com.campos.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookServices {

    private static final Logger logger = LoggerFactory.getLogger(BookServices.class);

    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> findAll() {
        logger.info("Finding all books");
        var bookDTOs = parseListObjects(bookRepository.findAll(), BookDTO.class);
        bookDTOs.forEach(this::addHateoasBook);
        return bookDTOs;
    }
    
    public BookDTO findById(Long id) {
        logger.info("Finding a book with id: {}", id);
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this id"));
        var dto = parseObject(entity, BookDTO.class);
        addHateoasBook(dto);
        return dto;
    }

    public BookDTO create(BookDTO bookDTO) {
        if (bookDTO == null) throw new RequiredObjectIsNullException("Book cannot be null");
        logger.info("Adding a new book");
        var entity = parseObject(bookDTO, Books.class);
        var dto = parseObject(bookRepository.save(entity), BookDTO.class);
        addHateoasBook(dto);
        return dto;
    }

    public BookDTO update(Long id, BookDTO bookDTO) {
        if (bookDTO == null) throw new RequiredObjectIsNullException("Book cannot be null");
        logger.info("Updating a book");
        Books entity = bookRepository.findById(id)
                .orElseThrow(() -> new RequiredObjectIsNullException("Id cannot be null"));
        entity.setAuthor(bookDTO.getAuthor());
        entity.setLaunchDate(bookDTO.getLaunchDate());
        entity.setPrice(bookDTO.getPrice());
        entity.setTitle(bookDTO.getTitle());

        var dto = parseObject(bookRepository.save(entity), BookDTO.class);
        addHateoasBook(bookDTO);
        return dto;
    }

    public void delete(Long id) {
        logger.info("deleting a book");
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this id"));
        bookRepository.delete(entity);
    }

    private void addHateoasBook(BookDTO bookDTO) {
        bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getId())).withSelfRel().withType("GET"));
        bookDTO.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        bookDTO.add(linkTo(methodOn(BookController.class).create(bookDTO)).withRel("create").withType("POST"));
        bookDTO.add(linkTo(methodOn(BookController.class).update(bookDTO.getId(), bookDTO)).withRel("update").withType("PUT"));
        bookDTO.add(linkTo(methodOn(BookController.class).delete(bookDTO.getId())).withRel("delete").withType("DELETE"));
    }
}
