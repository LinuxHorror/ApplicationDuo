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
    @Mapping(target = "count", source = "count")
    @Mapping(target = "imageToShow", source = "imageToShow")
    ProductEntity toEntity(ProductDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "productTitle", source = "productTitle")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "count", source = "count")
    @Mapping(target = "imageToShow", source = "imageToShow")
    ProductDto toDto(ProductEntity entity);

    List<ProductEntity> toListEntity(List<ProductDto> dtos);
    List<ProductDto> toListDto(List<ProductEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imageToShow", ignore = true)
    // TODO : ADD POSSIBILITY TO CHANGE IMAGE
    void update(@MappingTarget ProductEntity entity, ProductDto dto);

}
