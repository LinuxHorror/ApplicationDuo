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
    private final ProductMapper productMapper;

    public void save(ProductEntity product, Integer count) {
        CartEntity cartEntity = productMapper.toCartEntity(product);
        cartEntity.setCount(count);
        CurrentUser.entity.addToCart(cartEntity);

        repository.searchCartEntityByProductId(product.getId())
                .ifPresentOrElse(cartEntity2 -> cartEntity2.setCount(count),
                                () -> repository.save(cartEntity));
    }

    public void deleteCartProduct(Integer id) {
        CartEntity byProductId = repository.findByProductId(id);

        List<CartEntity> cart = CurrentUser.entity.getCart();
        if(cart.contains(byProductId)){
            cart.remove(byProductId);
            repository.delete(byProductId);
        }
    }
}
