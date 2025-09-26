package br.com.campos.unitETests.mapper.mocks;

import br.com.campos.data.dto.v1.BookDTO;
import br.com.campos.model.Books;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockBook {


    public Books mockEntity() {
        return mockEntity(0);
    }
    
    public BookDTO mockDTO() {
        return mockDTO(0);
    }
    
    public List<Books> mockEntityList() {
        List<Books> book = new ArrayList<Books>();
        for (int i = 0; i < 14; i++) {
            book.add(mockEntity(i));
        }
        return book;
    }

    public List<BookDTO> mockDTOList() {
        List<BookDTO> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockDTO(i));
        }
        return persons;
    }
    
    public Books mockEntity(Integer number) {
        Books book = new Books();
        book.setId(number.longValue());
        book.setAuthor("Author name" + number);
        book.setLaunchDate(LocalDateTime.of(2025, 5, 25, 0, 0, 0));
        book.setPrice(49.00 + number);
        book.setTitle("Title book" + number);
        return book;
    }

    public BookDTO mockDTO(Integer number) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor("Author name");
        bookDTO.setLaunchDate(LocalDateTime.of(2025, 5, 25, 0, 0, 0));
        bookDTO.setPrice(49.00);
        bookDTO.setTitle("Title book");
        return bookDTO;
    }

}