package ru.sergey_white.reactiveexample.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.sergey_white.reactiveexample.model.dto.UserDto;
import ru.sergey_white.reactiveexample.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserDto dto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget User existing, UserDto dto);
}