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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository repository;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    public void save(ProductEntity product, Integer count) {
        CartEntity cartEntity = productMapper.toCartEntity(product);
        cartEntity.setCount(count);
        CurrentUser.entity.addToCart(cartEntity);
        Optional<CartEntity> cartEntity1 = repository.searchCartEntityByProductId(product.getId());
        if (cartEntity1.isPresent()) {
            cartEntity1.get().setCount(count);
        } else {
            repository.save(cartEntity);
        }
    }

    public void deleteCartProduct(Integer id) {
        CartEntity byProductId = repository.findByProductId(id);
        // TODO REMOVE FROM LIST TO SHOW IN UI CORRECT INFO
        // TODO TRY SMTH WITH ORPHAN REMOVAL


        List<CartEntity> cart = CurrentUser.entity.getCart();
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).equals(byProductId)) {
                cart.remove(i);
                repository.deleteCartProduct(id);
                break;
            }
        }
    }
}
