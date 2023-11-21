package com.example.applicationduo.repositories;

import com.example.applicationduo.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity, Byte> {
    Optional<ImageEntity> findByName (String name);
}
