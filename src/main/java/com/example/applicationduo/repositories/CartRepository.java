package com.example.applicationduo.repositories;


import com.example.applicationduo.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {

    CartEntity findByProductIdAndUserId(Integer id, UUID userId);

    @Modifying
    @Query("DELETE FROM CartEntity WHERE productId = :id")
    void deleteCartProduct(Integer id);

    Optional<CartEntity> searchCartEntityByProductIdAndUserId(Integer id, UUID userId);
}
