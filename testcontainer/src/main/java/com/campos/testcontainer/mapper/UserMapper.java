package com.campos.testcontainer.mapper;

import com.campos.testcontainer.data.dto.UserResponseDto;
import com.campos.testcontainer.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponseDto toResponseDto(User entity) {
        if (entity == null) return null;

        return new UserResponseDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getGender(),
                entity.getState()
        );
    }

    public List<UserResponseDto> toResponseListDto(List<User> entityList) {
        if (entityList == null) return null;

        return entityList.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}
