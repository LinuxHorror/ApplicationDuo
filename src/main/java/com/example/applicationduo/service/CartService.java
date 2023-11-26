package com.example.applicationduo.service;

import com.example.applicationduo.entity.CartEntity;
import com.example.applicationduo.entity.ProductEntity;
import com.example.applicationduo.mappers.CartMapper;
import com.example.applicationduo.mappers.ProductMapper;
import com.example.applicationduo.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository repository;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    public void save(ProductEntity product) {
        CartEntity cartEntity = productMapper.toCartEntity(product);
        repository.save(cartEntity);
    }
}
