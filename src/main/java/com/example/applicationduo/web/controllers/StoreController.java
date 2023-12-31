package com.example.applicationduo.web.controllers;


import com.example.applicationduo.dto.ProductDto;
import com.example.applicationduo.entity.ProductEntity;
import com.example.applicationduo.exceptions.UserNotRegisteredException;
import com.example.applicationduo.model.CurrentUser;
import com.example.applicationduo.service.CartService;
import com.example.applicationduo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static java.util.Objects.isNull;


@RequiredArgsConstructor
@Controller
@RequestMapping("/store")
public class StoreController {
    private final ProductService productService;
    private final CartService cartService;

    @GetMapping
    public ModelAndView mainPage(@ModelAttribute("product") ProductDto productDto) {
        var modelAndView = new ModelAndView("storePage");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @PostMapping("/addToCart/{idProduct}")
    public ModelAndView addToCart(@PathVariable("idProduct") Integer id,
                                  @RequestParam("quantity") Integer count) throws UserNotRegisteredException {
        if (isNull(CurrentUser.entity)) {
            throw new UserNotRegisteredException();
        }
        Optional<ProductEntity> product = productService.getById(id);
        if (count <= product.get().getCount()) {
            ProductEntity productEntity = product.get();
            cartService.save(productEntity, count);
        }

        return new ModelAndView("redirect:/store");
    }


}
