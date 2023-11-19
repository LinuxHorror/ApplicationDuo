package com.example.applicationduo.mappers;

import com.example.applicationduo.dto.ProductDto;
import com.example.applicationduo.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface ProductMapper {

    @Mapping(target = "productTitle", source = "productTitle")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    ProductEntity toEntity(ProductDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "productTitle", source = "productTitle")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    ProductDto toDto(ProductEntity entity);

    List<ProductEntity> toListEntity(List<ProductDto> dtos);
    List<ProductDto> toListDto(List<ProductEntity> entities);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget ProductEntity entity, ProductDto dto);

}
