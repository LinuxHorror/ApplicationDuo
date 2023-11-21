package com.example.applicationduo.service;

import com.example.applicationduo.dto.ProductDto;
import com.example.applicationduo.entity.ProductEntity;
import com.example.applicationduo.mappers.ProductMapper;
import com.example.applicationduo.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public void save(ProductEntity product){
        repository.save(product);
    }
    public List<ProductEntity> findAll(){
        return repository.findAll();
    }
    public Optional<ProductEntity> getById(Integer id){
        return repository.findById(id);
    }
    public void deleteById(Integer id){
        repository.deleteById(id);
    }
    @Transactional
    public void update(Integer id, ProductDto dto){
        mapper.update(repository.getReferenceById(id), dto);
    }
}
