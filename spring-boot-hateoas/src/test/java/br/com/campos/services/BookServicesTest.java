package br.com.campos.services;

import br.com.campos.data.dto.v1.BookDTO;
import br.com.campos.exception.RequiredObjectIsNullException;
import br.com.campos.model.Books;
import br.com.campos.repository.BookRepository;
import br.com.campos.unitETests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

    MockBook input;

    @InjectMocks
    private BookServices services;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Books book = input.mockEntity();
        book.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(book));
        var result = services.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") &&
                        link.getHref().endsWith("/api/book/v1/1") &&
                        link.getType().equals("GET")
                ));

                assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") &&
                        link.getHref().endsWith("/api/book/v1") &&
                        link.getType().equals("GET")
                ));

                assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") &&
                        link.getHref().endsWith("/api/book/v1") &&
                        link.getType().equals("POST")
                ));

                assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") &&
                        link.getHref().endsWith("/api/book/v1/1") &&
                        link.getType().equals("PUT")
                ));

                assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete") &&
                        link.getHref().endsWith("/api/book/v1/1") &&
                        link.getType().equals("DELETE")
                ));

        assertEquals("Author name0", result.getAuthor());
        assertEquals(LocalDateTime.of(2025, 05, 25, 0, 0, 0), result.getLaunchDate());
        assertEquals(49.00, result.getPrice());
        assertEquals("Title book0", result.getTitle());
    }

    @Test
    void create() {

        Books book = input.mockEntity(1);
        Books persisted = book;
        persisted.setId(1L);

        BookDTO dto = input.mockDTO(1);

        when(repository.save(any(Books.class))).thenReturn(persisted);
        var result = services.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") &&
                        link.getHref().endsWith("/api/book/v1/1") &&
                        link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") &&
                        link.getHref().endsWith("/api/book/v1") &&
                        link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") &&
                        link.getHref().endsWith("/api/book/v1") &&
                        link.getType().equals("POST")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") &&
                        link.getHref().endsWith("/api/book/v1/1") &&
                        link.getType().equals("PUT")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete") &&
                        link.getHref().endsWith("/api/book/v1/1") &&
                        link.getType().equals("DELETE")
                ));

        assertEquals("Author name1", result.getAuthor());
        assertEquals(LocalDateTime.of(2025, 05, 25, 0, 0, 0), result.getLaunchDate());
        assertEquals(50.00, result.getPrice());
        assertEquals("Title book1", result.getTitle());
    }

    @Test
    void testCreateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    services.create(null);
                });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertFalse(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {

        Books book = input.mockEntity(1);
        Books persisted = book;
        persisted.setId(1L);

        BookDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(book));
        when(repository.save(book)).thenReturn(persisted);

        var result = services.update(1L, dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") &&
                        link.getHref().endsWith("/api/book/v1/1") &&
                        link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") &&
                        link.getHref().endsWith("/api/book/v1") &&
                        link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") &&
                        link.getHref().endsWith("/api/book/v1") &&
                        link.getType().equals("POST")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") &&
                        link.getHref().endsWith("/api/book/v1") &&
                        link.getType().equals("PUT")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete") &&
                        link.getHref().endsWith("/api/book/v1/1") &&
                        link.getType().equals("DELETE")
                ));

        assertEquals("Author name", result.getAuthor());
        assertEquals(LocalDateTime.of(2025, 05, 25, 0, 0, 0), result.getLaunchDate());
        assertEquals(49.00, result.getPrice());
        assertEquals("Title book", result.getTitle());
    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    services.update(null, null);
                });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertFalse(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete() {
        Books book = input.mockEntity(1);
        book.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(book));

        services.delete(1L);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Books.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {

        List<Books> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<BookDTO> bookDTOList =  services.findAll();

        var bookOne = bookDTOList.get(1);

        assertNotNull(bookOne);
        assertNotNull(bookOne.getId());
        assertNotNull(bookOne.getLinks());

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") &&
                        link.getHref().endsWith("/api/book/v1/1") &&
                        link.getType().equals("GET")
                ));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") &&
                        link.getHref().endsWith("/api/book/v1") &&
                        link.getType().equals("GET")
                ));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") &&
                        link.getHref().endsWith("/api/book/v1") &&
                        link.getType().equals("POST")
                ));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") &&
                        link.getHref().endsWith("/api/book/v1/1") &&
                        link.getType().equals("PUT")
                ));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete") &&
                        link.getHref().endsWith("/api/book/v1/1") &&
                        link.getType().equals("DELETE")
                ));

        assertEquals("Author name1", bookOne.getAuthor());
        assertEquals(LocalDateTime.of(2025, 5, 25, 0, 0, 0), bookOne.getLaunchDate());
        assertEquals(50.00, bookOne.getPrice());
        assertEquals("Title book1", bookOne.getTitle());

        var bookFour = bookDTOList.get(4);

        assertNotNull(bookFour);
        assertNotNull(bookFour.getId());
        assertNotNull(bookFour.getLinks());

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") &&
                        link.getHref().endsWith("/api/book/v1/4") &&
                        link.getType().equals("GET")
                ));

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") &&
                        link.getHref().endsWith("/api/book/v1") &&
                        link.getType().equals("GET")
                ));

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") &&
                        link.getHref().endsWith("/api/book/v1") &&
                        link.getType().equals("POST")
                ));

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") &&
                        link.getHref().endsWith("/api/book/v1/4") &&
                        link.getType().equals("PUT")
                ));

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete") &&
                        link.getHref().endsWith("/api/book/v1/4") &&
                        link.getType().equals("DELETE")
                ));

        assertEquals("Author name4", bookFour.getAuthor());
        assertEquals(LocalDateTime.of(2025, 5, 25, 0, 0, 0), bookFour.getLaunchDate());
        assertEquals(53.00, bookFour.getPrice());
        assertEquals("Title book4", bookFour.getTitle());

        var bookNine = bookDTOList.get(9);

        assertNotNull(bookNine);
        assertNotNull(bookNine.getId());
        assertNotNull(bookNine.getLinks());

        assertNotNull(bookNine.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") &&
                        link.getHref().endsWith("/api/book/v1/9") &&
                        link.getType().equals("GET")
                ));

        assertNotNull(bookNine.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") &&
                        link.getHref().endsWith("/api/book/v1") &&
                        link.getType().equals("GET")
                ));

        assertNotNull(bookNine.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") &&
                        link.getHref().endsWith("/api/book/v1") &&
                        link.getType().equals("POST")
                ));

        assertNotNull(bookNine.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") &&
                        link.getHref().endsWith("/api/book/v1/9") &&
                        link.getType().equals("PUT")
                ));

        assertNotNull(bookNine.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete") &&
                        link.getHref().endsWith("/api/book/v1/9") &&
                        link.getType().equals("DELETE")
                ));

        assertEquals("Author name9", bookNine.getAuthor());
        assertEquals(LocalDateTime.of(2025, 05, 25, 0, 0, 0), bookNine.getLaunchDate());
        assertEquals(58.00, bookNine.getPrice());
        assertEquals("Title book9", bookNine.getTitle());
    }
}