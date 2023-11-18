package com.example.applicationduo.mappers;


import com.example.applicationduo.dto.UserCreationDto;
import com.example.applicationduo.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "string"
)
public interface UserMapper {
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "email", source = "email")
    UserEntity toEntity(UserCreationDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "email", source = "email")
    UserCreationDto toDto(UserEntity entity);

    List<UserEntity> toListEntity (List<UserCreationDto> dtos);
    List<UserCreationDto> toListDto (List<UserEntity> entities);
}
