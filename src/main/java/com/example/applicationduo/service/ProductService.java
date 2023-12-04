package com.example.applicationduo.service;

import com.example.applicationduo.dto.ProductDto;
import com.example.applicationduo.entity.ProductEntity;
import com.example.applicationduo.mappers.ProductMapper;
import com.example.applicationduo.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.PanelUI;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
@Getter
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductEntity save(ProductEntity product) {
        return repository.save(product);
    }
    public List<ProductDto> findAll(){
        return mapper.toListDto(repository.findAll());
    }
    public Optional<ProductEntity> getById(Integer id){
        return repository.findById(id);
    }

    public List<ProductEntity> getByTitle(String title){
        return repository.findByProductTitle(title);
    }
    public void deleteById(Integer id){
        repository.deleteById(id);
    }
    @Transactional
    public void update(Integer id, ProductDto dto){
        mapper.update(repository.getReferenceById(id), dto);
    }

    @Transactional
    public List<ProductDto> getDesc(){
        return mapper.toListDto(repository.findAllDesc());
    }
    @Transactional
    public List<ProductDto> getAsc(){
        return mapper.toListDto(repository.findAllAsc());
    }
    public void deleteAll(){
        repository.deleteAll();
    }
}
