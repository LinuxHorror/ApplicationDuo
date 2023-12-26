package com.example.applicationduo.mappers;


import com.example.applicationduo.dto.UserCreationDto;
import com.example.applicationduo.entity.UserEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Mapping(target = "role", defaultValue = "ROLE_ADMIN")
    @Mapping(target = "password", expression = "java(encodePassword(dto))")
    @Mapping(target = "username", source = "username")
    UserEntity toEntity(UserCreationDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "username", source = "username")
    UserCreationDto toDto(UserEntity entity);

    List<UserEntity> toListEntity(List<UserCreationDto> dtos);

    List<UserCreationDto> toListDto(List<UserEntity> entities);

    default String encodePassword(UserCreationDto dto){
        return encoder.encode(dto.getPassword());
    }
}
