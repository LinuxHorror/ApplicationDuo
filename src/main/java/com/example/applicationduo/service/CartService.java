package com.example.applicationduo.service;

import com.example.applicationduo.entity.CartEntity;
import com.example.applicationduo.entity.ProductEntity;
import com.example.applicationduo.mappers.CartMapper;
import com.example.applicationduo.mappers.ProductMapper;
import com.example.applicationduo.model.CurrentUser;
import com.example.applicationduo.repositories.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository repository;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    public void save(ProductEntity product, Integer count) {
        CartEntity cartEntity = productMapper.toCartEntity(product);
        cartEntity.setCount(count);
        CurrentUser.entity.addToCart(cartEntity);
        repository.save(cartEntity);
    }
    @Transactional
    public void deleteCartProduct(Integer id){
        CartEntity byProductId = repository.findByProductId(id);
        // TODO REMOVE FROM LIST TO SHOW IN UI CORRECT INFO
        // TODO TRY SMTH WITH ORPHAN REMOVAL
        CurrentUser.entity.getCart().remove(byProductId);
        repository.deleteCartProduct(id);
    }
}
