package com.example.applicationduo.web.controllers;


import com.example.applicationduo.mappers.ProductMapper;
import com.example.applicationduo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor

@Controller
@RequestMapping("/store")
public class StoreController {
    private final ProductService service;
    private final ProductMapper mapper;
    @GetMapping
    public ModelAndView mainPage(){
        return new ModelAndView("mainPage");

    }
}
