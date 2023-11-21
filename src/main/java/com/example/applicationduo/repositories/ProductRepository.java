package com.example.applicationduo.repositories;

import com.example.applicationduo.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    //TODO native? query desc order
    Optional<ProductEntity> findByProductTitle(String name);

    @Query(nativeQuery = true, value =  "SELECT * FROM products pr order by pr.price DESC")
    List<ProductEntity> findAllDesc();

    @Query(nativeQuery = true, value =  "SELECT * FROM products pr ORDER BY pr.price ASC")
    List<ProductEntity> findAllAsc();
}
