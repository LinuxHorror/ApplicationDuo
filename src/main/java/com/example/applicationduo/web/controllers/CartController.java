package com.example.applicationduo.web.controllers;


import com.example.applicationduo.dto.ProductDto;
import com.example.applicationduo.entity.CartEntity;
import com.example.applicationduo.entity.ProductEntity;
import com.example.applicationduo.entity.UserEntity;
import com.example.applicationduo.exceptions.UserNotRegisteredException;
import com.example.applicationduo.mappers.ProductMapper;
import com.example.applicationduo.model.CurrentUser;
import com.example.applicationduo.service.CartService;
import com.example.applicationduo.service.ProductService;
import com.example.applicationduo.utils.ProductComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Controller
@RequestMapping("*/cart")
public class CartController {
    private final ProductService service;
    private final CartService cartService;

    @GetMapping
    public ModelAndView shoppingCart() throws UserNotRegisteredException {
        ModelAndView modelAndView = new ModelAndView("cartPage");
        if (isNull(CurrentUser.entity)) {
            throw new UserNotRegisteredException();
        }
        List<CartEntity> cart = CurrentUser.entity.getCart();
        List<ProductEntity> products = new ArrayList<>();
        for (CartEntity entity : cart) {
            ProductEntity productEntity = service.getById(entity.getProductId()).get();
            productEntity.setCount(entity.getCount());
            products.add(productEntity);
        }
        List<ProductDto> listDto = service.getMapper().toListDto(products);
        modelAndView.addObject("products", listDto);
        float sumOfPurchase = 0;
        for (ProductEntity product : products) {
            sumOfPurchase += product.getCount() * product.getPrice();
        }
        modelAndView.addObject("totalPurchase", sumOfPurchase);

        return modelAndView;
    }

    @GetMapping("/sort")
    public ModelAndView getSortedPage() {
        ModelAndView modelAndView = shoppingCart();
        List<ProductEntity> all = service.findAll();
        all.sort(new ProductComparator());
        modelAndView.addObject("products", service.getMapper().toListDto(all));
        return modelAndView;
    }

    @PostMapping("/{id}/remove")
    public String removeFromCart(@PathVariable("id") Integer id) {
        cartService.deleteCartProduct(id);
        return "redirect:/store/cart";
    }

    @PostMapping("/acceptPurchase")
    public ModelAndView accept(@RequestParam("order") Float sumOfPurchase) {
        service.deleteAll();
        return new ModelAndView("redirect:/cart");
    }
}
