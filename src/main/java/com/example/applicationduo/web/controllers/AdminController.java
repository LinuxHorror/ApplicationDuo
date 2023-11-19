package com.example.applicationduo.web.controllers;


import com.example.applicationduo.dao.ProductService;
import com.example.applicationduo.dao.UserService;
import com.example.applicationduo.dto.ProductDto;
import com.example.applicationduo.mappers.ProductMapper;
import com.example.applicationduo.mappers.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;
    private final UserService userService;

    private final UserMapper userMapper;
    private final ProductMapper productMapper;

    @GetMapping
    public ModelAndView getTotalPage(@ModelAttribute("newProduct") ProductDto productDto){
        ModelAndView modelAndView = new ModelAndView("adminPage");
        modelAndView.addObject("users", userMapper.toListDto(userService.findAll()));
        modelAndView.addObject("products", productMapper.toListDto(productService.findAll()));
        return modelAndView;
    }

    @PostMapping("/updateProduct/{id}")
    public String update(@Valid @ModelAttribute("newProduct") ProductDto dto,
                         BindingResult bindingResult,
                         @PathVariable("id") UUID id){
        if(!bindingResult.hasFieldErrors()){
            productService.update(id, dto);
        }
        return "adminPage";
    }

    /*@ModelAttribute(name = "newProduct")
    public ProductDto productDto(){
        return new ProductDto();
    }*/
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute(name = "newProduct") ProductDto product,
                       BindingResult result) {

        if (!result.hasFieldErrors()) {
            productService.save(productMapper.toEntity(product));
        }
        return "adminPage";
    }
}
