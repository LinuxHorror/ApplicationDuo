package com.example.applicationduo.repositories;


import com.example.applicationduo.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {
}
