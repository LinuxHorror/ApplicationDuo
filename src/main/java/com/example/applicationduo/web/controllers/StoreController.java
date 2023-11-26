package com.example.applicationduo.web.controllers;


import com.example.applicationduo.dto.ProductDto;
import com.example.applicationduo.entity.ProductEntity;
import com.example.applicationduo.mappers.ProductMapper;
import com.example.applicationduo.service.CartService;
import com.example.applicationduo.service.ProductService;
import lombok.RequiredArgsConstructor;
import com.example.applicationduo.mappers.ProductMapper;
import com.example.applicationduo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/store")
public class StoreController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CartService cartService;

    @GetMapping
    public ModelAndView mainPage(@ModelAttribute("product") ProductDto productDto) {
        var modelAndView = new ModelAndView("storePage");
        modelAndView.addObject("products", productMapper.toListDto(productService.findAll()));
        return modelAndView;
    }

    @PostMapping("/addToCart/{idProduct}")
    public ModelAndView addToCart(@PathVariable("idProduct") Integer id,
                                  @RequestParam("quantity") Integer count) {

        Optional<ProductEntity> product = productService.getById(id);
        if (count <= product.get().getCount()) {
            ProductEntity productEntity = product.get();
            productEntity.setCount(count);
            cartService.save(productEntity);
        }
        return new ModelAndView("redirect:/store");
    }


}
