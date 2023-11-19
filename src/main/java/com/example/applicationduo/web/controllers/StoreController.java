package com.example.applicationduo.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/store")
public class StoreController {
    @GetMapping
    public ModelAndView mainPage(){
        return new ModelAndView("storeMainPage");
    }
}
