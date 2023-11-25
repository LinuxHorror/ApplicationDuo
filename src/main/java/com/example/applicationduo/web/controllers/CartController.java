package com.example.applicationduo.web.controllers;


import com.example.applicationduo.entity.ProductEntity;
import com.example.applicationduo.mappers.ProductMapper;
import com.example.applicationduo.service.ProductService;
import com.example.applicationduo.utils.ProductComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("*/cart")
public class CartController {

    private final ProductService service;
    private final ProductMapper mapper;
    @GetMapping
    public ModelAndView shoppingCart(){
        ModelAndView modelAndView = new ModelAndView("cartPage");
        List<ProductEntity> allProducts = service.findAll();
        modelAndView.addObject("products", mapper.toListDto(allProducts));
        float sumOfPurchase = 0;
        for(ProductEntity product : allProducts){
            sumOfPurchase += product.getCount() * product.getPrice();
        }
        modelAndView.addObject("totalPurchase", sumOfPurchase);
        return modelAndView;
    }
    @GetMapping("/sort")
    public ModelAndView getSortedPage(){
        ModelAndView modelAndView = shoppingCart();
        List<ProductEntity> all = service.findAll();
        all.sort(new ProductComparator());
        modelAndView.addObject("products", mapper.toListDto(all));
        return modelAndView;
    }

    @PostMapping("/{id}/remove")
    public String removeFromCart(@PathVariable("id") Integer id){
        service.deleteById(id);
        return "redirect:/store/cart";
    }
}
