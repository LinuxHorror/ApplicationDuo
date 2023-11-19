package com.example.applicationduo.dao;

import com.example.applicationduo.dto.ProductDto;
import com.example.applicationduo.entity.ProductEntity;
import com.example.applicationduo.entity.UserEntity;
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
    public ProductEntity update(Integer id, ProductDto dto){
        return repository.getReferenceById(id)
                .setProductTitle(dto.getProductTitle())
                .setPrice(dto.getPrice())
                .setDescription(dto.getDescription());
    }
}
