package com.campos.testcontainer.services;

import com.campos.testcontainer.data.dto.bookdot.BookCreateDto;
import com.campos.testcontainer.data.dto.bookdot.BookResponseDto;
import com.campos.testcontainer.data.dto.bookdot.BookUpdateDto;
import com.campos.testcontainer.entities.Book;
import com.campos.testcontainer.entities.User;
import com.campos.testcontainer.mapper.BookMapper;
import com.campos.testcontainer.repositories.BookRepository;
import com.campos.testcontainer.repositories.UserRepository;
import com.campos.testcontainer.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookMapper bookMapper;

    @Transactional(readOnly = true)
    public List<BookResponseDto> findAll() {
        logger.info("Finding all books");
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookResponseDto findById(Long id) {
        logger.info("Finding one book");
        Book bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return BookMapper.toResponseDto(bookEntity);
    }

    @Transactional
    public BookResponseDto create(BookCreateDto dto) {
        logger.info("Creating one book");
        // create a book entity from dto
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setPrice(dto.getPrice());
        book.setLaunchDate(dto.getLaunchDate());

        //link authors if they are provide
        if (dto.getAuthorsIds() != null && !dto.getAuthorsIds().isEmpty()) {
            Set<User> authors = findAuthorsById(dto.getAuthorsIds());

            // establishes a bidirectional relationship
            for (User author : authors) {
                author.getAuthoredBooks().add(book);
                book.getAuthors().add(author);
            }
        }
        //save the book (cascade go to save the relationship)
        Book savedBook = bookRepository.save(book);

        logger.info("Book created successfully with id: {}", savedBook.getId());
        return BookMapper.toResponseDto(savedBook);
    }

    @Transactional
    public BookResponseDto update(Long id, BookUpdateDto dto) {
        logger.info("Updating one book");

        //search book
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        // Update campus book
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setPrice(dto.getPrice());
        book.setLaunchDate(dto.getLaunchDate());

        // Update authors if they are provide
        if (dto.getAuthorsIds() != null) {
            // remove old relation
            for (User author : book.getAuthors()) {
                author.getAuthoredBooks().remove(book);
            }
            book.getAuthors().clear();

            // add new relationship
            if (!dto.getAuthorsIds().isEmpty()) {
                Set<User> newAuthors = findAuthorsById(dto.getAuthorsIds());
                for (User author : newAuthors) {
                    author.getAuthoredBooks().add(book);
                    book.getAuthors().add(author);
                }
            }
        }
        // save alterations
        Book updatedBook = bookRepository.save(book);

        logger.info("Book updated successfully: {}", updatedBook.getId());
        return BookMapper.toResponseDto(updatedBook);
    }

    @Transactional
    public void delete(Long id) {
        logger.info("Deleting one book");
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        // remove relationship before delete
        for (User author : book.getAuthors()) {
            author.getAuthoredBooks().remove(book);
        }
        book.getAuthors().clear();

        bookRepository.delete(book);
        logger.info("Book deleted successfully: {}", id);
    }
    /*
    * Add an author to an existing book.
    *
    * @param bookId - book id
    * @param authorId - author id to add
    * @return BookResponseDto with author added
    * */
    @Transactional
    public BookResponseDto addAuthorToBook(Long bookId, Long authorId) {
        logger.info("Adding an author with id: {} to an existing book with id: {} ", authorId, bookId);

        // Search book by id
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        // Search user by id
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));

        // verify if the author is already in the database
        if (book.getAuthors().contains(author)) {
            throw new ResourceNotFoundException(
                    String.format("Author with id %d is already linked to book with id %d",
                            authorId, bookId)
            );
        }
        book.getAuthors().add(author);
        author.getAuthoredBooks().add(book);

        Book updatedAuthorToBook = bookRepository.save(book);
        logger.info("Author [id={}] successfully added to book [id={}]", authorId, bookId);
        return BookMapper.toResponseDto(updatedAuthorToBook);
    }

    @Transactional
    public BookResponseDto removeAuthorFromBook(Long bookId, Long authorId) {
        logger.info("Removing author with id: {} from book with id: {}", authorId, bookId);

        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found author with id: " + authorId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found book with id: " + bookId));

        if (!book.getAuthors().contains(author)) {
            throw new ResourceNotFoundException(String.format("Cannot remove: Author with id %d is not linked to book with id %d",
                    authorId, bookId));
        }
        book.getAuthors().remove(author);
        author.getAuthoredBooks().remove(book);

        Book updatedBookAfterRemoveAuthor = bookRepository.save(book);
        logger.info("Author [id={}] successfully removed from book [id={}]", authorId, bookId);
        return BookMapper.toResponseDto(updatedBookAfterRemoveAuthor);
    }

    /*
     * Search authors (Users) by ids if it was provided
     * release exception if id is not found
     * */
    private Set<User> findAuthorsById(List<Long> authorsIds) {
        Set<User> authors = new HashSet<>();

        for (Long authorId : authorsIds) {
            User author = userRepository.findById(authorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));
            authors.add(author);
        }
        logger.info("Found {} authors", authors.size());
        return authors;
    }
}
