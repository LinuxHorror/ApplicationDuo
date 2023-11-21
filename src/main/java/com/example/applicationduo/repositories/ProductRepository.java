package com.example.applicationduo.repositories;

import com.example.applicationduo.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    //TODO native? query desc order
    List<ProductEntity> findByProductTitle(String name);
}
