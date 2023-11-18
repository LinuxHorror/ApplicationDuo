package com.example.applicationduo.web;

import com.example.applicationduo.dto.UserCreationDto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @GetMapping
    public ModelAndView regPage(@ModelAttribute("newUser") UserCreationDto user){
        var modelAndView = new ModelAndView("registration");
        return modelAndView;
    }




}
