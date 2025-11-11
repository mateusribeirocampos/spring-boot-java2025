package com.campos.testcontainer.controllers;

import com.campos.testcontainer.data.dto.bookdot.BookCreateDto;
import com.campos.testcontainer.data.dto.bookdot.BookResponseDto;
import com.campos.testcontainer.data.dto.bookdot.BookUpdateDto;
import com.campos.testcontainer.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/books/v1")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookResponseDto>> findAll() {
        logger.info("GET /api/books/v1 - Finding all books");
        List<BookResponseDto> bookListDto = bookService.findAll();
        return ResponseEntity.ok().body(bookListDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponseDto> findById(@PathVariable Long id) {
        logger.info("GET /api/books/v1/{} - Finding one book", id);
        BookResponseDto bookDto = bookService.findById(id);
        return ResponseEntity.ok().body(bookDto);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponseDto> create(@RequestBody BookCreateDto dto) {
        logger.info("POST /api/books/v1 - creating book - {}", dto.getTitle());

        BookResponseDto createdBook = bookService.create(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(createdBook.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createdBook);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponseDto> update(
            @PathVariable Long id,
            @RequestBody BookUpdateDto book) {
        logger.info("PUT /api/books/v1/{} - updating book", id);
        BookResponseDto updatedBook = bookService.update(id, book);
        return ResponseEntity.ok().body(updatedBook);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("DELETE /api/books/v1/{} - deleting book", id);
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{bookId}/authors/{authorId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponseDto> addAuthorToBook(
            @PathVariable Long bookId,
            @PathVariable Long authorId
    ) {
        logger.info("POST /api/books/v1/{}/authors/{} - adding author in book", bookId, authorId);
        BookResponseDto updateBookAddAuthor = bookService.addAuthorToBook(bookId, authorId);
        return ResponseEntity.ok().body(updateBookAddAuthor);
    }

    @DeleteMapping(value = "/{bookId}/authors/{authorId}")
    public ResponseEntity<BookResponseDto> removeAuthorFromBook(
            @PathVariable Long bookId,
            @PathVariable Long authorId) {
        logger.info("DELETE /api/books/v1/{}/authors/{} - removing author from book", bookId, authorId);
        BookResponseDto updatedBookRemoveAuthor = bookService.removeAuthorFromBook(bookId, authorId);
        return ResponseEntity.ok().body(updatedBookRemoveAuthor);
    }
}