package com.example.applicationduo.repositories;

import com.example.applicationduo.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findByProductTitle(String name);
}
