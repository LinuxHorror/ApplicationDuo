package com.example.applicationduo.repositories;

import com.example.applicationduo.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Byte> {
}
