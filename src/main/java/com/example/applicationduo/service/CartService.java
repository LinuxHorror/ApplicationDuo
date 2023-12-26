package com.example.applicationduo.service;

import com.example.applicationduo.entity.CartEntity;
import com.example.applicationduo.entity.ProductEntity;
import com.example.applicationduo.entity.UserEntity;
import com.example.applicationduo.mappers.ProductMapper;
import com.example.applicationduo.mappers.UserMapper;
import com.example.applicationduo.model.CurrentUser;
import com.example.applicationduo.repositories.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository repository;
    private final ProductMapper productMapper;

    @Transactional
    public void save(ProductEntity product, Integer count) {
        CartEntity cartEntity = productMapper.toCartEntity(product);
        cartEntity.setCount(count);
//        CurrentUser.entity.addToCart(cartEntity);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity principal = (UserEntity)authentication.getPrincipal();
        principal.addToCart(cartEntity);
        repository.searchCartEntityByProductIdAndUserId(product.getId(), /*CurrentUser.entity.getId()*/ principal.getId())
                .ifPresentOrElse(cartEntity2 -> cartEntity2.setCount(count),
                                () -> repository.save(cartEntity));

    }

    public void deleteCartProduct(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity principal = (UserEntity)authentication.getPrincipal();
        CartEntity byProductId = repository.findByProductIdAndUserId(id,/* CurrentUser.entity.getId()*/ principal.getId());
//        List<CartEntity> cart = CurrentUser.entity.getCart();
        List<CartEntity> cart = principal.getCart();
        if (cart.contains(byProductId)) {
            cart.remove(byProductId);
            repository.delete(byProductId);
        }
    }
}
