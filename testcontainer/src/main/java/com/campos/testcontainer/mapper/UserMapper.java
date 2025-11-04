package com.campos.testcontainer.mapper;

import com.campos.testcontainer.data.dto.UserResponseDto;
import com.campos.testcontainer.entities.User;
import org.springframework.stereotype.Component;

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
}
