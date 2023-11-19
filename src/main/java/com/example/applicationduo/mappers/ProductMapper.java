package com.example.applicationduo.mappers;

import com.example.applicationduo.dto.ProductDto;
import com.example.applicationduo.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface ProductMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    ProductEntity toEntity(ProductDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    ProductDto toDto(ProductEntity entity);

    List<ProductEntity> toListEntity(List<ProductDto> dtos);
    List<ProductDto> toListDto(List<ProductEntity> entities);

}
