package com.campos.testcontainer.services;

import com.campos.testcontainer.data.dto.bookdot.BookResponseDto;
import com.campos.testcontainer.entities.Book;
import com.campos.testcontainer.mapper.BookMapper;
import com.campos.testcontainer.repositories.BookRepository;
import com.campos.testcontainer.services.exceptions.DatabaseException;
import com.campos.testcontainer.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

//    @Transactional(readOnly = true)
//    public List<Book> findAll() {
//        logger.info("Finding all books");
//        return bookRepository.findAll();
//    }

    @Transactional(readOnly = true)
    public List<BookResponseDto> findAll() {
        logger.info("Finding all books");
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

//    @Transactional(readOnly = true)
//    public Book findById(Long id) {
//        logger.info("Finding one book");
//        return bookRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException(id));
//    }

    @Transactional(readOnly = true)
    public BookResponseDto findById(Long id) {
        logger.info("Finding one book");
        Book bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return BookMapper.toResponseDto(bookEntity);
    }

    @Transactional
    public Book create(Book entity) {
        logger.info("Creating one book");
        return bookRepository.save(entity);
    }

    @Transactional
    public Book update(Long id, Book book) {
        logger.info("Updating one book");
        try {
            Book entity = bookRepository.getReferenceById(id);
            updatedBook(entity, book);
            return bookRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    private void updatedBook(Book entity, Book book) {
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        entity.setDescription(book.getDescription());
    }

    @Transactional
    public void delete(Long id) {
        logger.info("Deleting one book");
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        try {
            bookRepository.delete(book);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
