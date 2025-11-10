package com.campos.testcontainer.mapper;

import com.campos.testcontainer.data.dto.bookdot.BookResponseDto;
import com.campos.testcontainer.data.dto.userdto.UserSummaryDto;
import com.campos.testcontainer.entities.Book;
import com.campos.testcontainer.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public static BookResponseDto toResponseDto(Book entity) {
        if (entity == null) return null;

        BookResponseDto dto = new BookResponseDto();
        dto.setId(entity.getId());
        dto.setLaunchDate(entity.getLaunchDate());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());

        if (entity.getAuthors() != null && !entity.getAuthors().isEmpty()) {
            List<UserSummaryDto> authors = entity.getAuthors()
                    .stream().map(BookMapper::toUserSummary)
                    .collect(Collectors.toList());
            dto.setAuthor(authors);
        }
        return dto;
    }

    public static UserSummaryDto toUserSummary(User user) {
        return new UserSummaryDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    public static Book toEntity(BookResponseDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setPrice(dto.getPrice());
        book.setLaunchDate(dto.getLaunchDate());
    return book;
    }
}
