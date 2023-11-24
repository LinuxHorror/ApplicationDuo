package com.example.applicationduo.web.controllers;


import com.example.applicationduo.dto.ProductDto;
import com.example.applicationduo.mappers.ProductMapper;
import com.example.applicationduo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@RequiredArgsConstructor
@Controller
@RequestMapping("/store")
public class StoreController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    @GetMapping("/cart")
    public ModelAndView shoppingCart(){
        return new ModelAndView("cartPage");
    }
    @GetMapping
    public ModelAndView mainPage(@ModelAttribute("product") ProductDto productDto){
        var modelAndView = new ModelAndView("storePage");
        modelAndView.addObject("product", productMapper.toListDto(productService.findAll()));
        return modelAndView;
    }

    @PostMapping("/addToCart")
    public ModelAndView addToCart(@ModelAttribute("newProduct") ProductDto product){
        var modelAndView = new ModelAndView();

        return new ModelAndView("redirect:/store");
    }


}
