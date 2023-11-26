package com.example.applicationduo.mappers;

import com.example.applicationduo.dto.CartDto;
import com.example.applicationduo.entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface CartMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "count", source = "count")
    CartEntity toEntity(CartDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "count", source = "count")
    CartDto toDto(CartEntity entity);


}
