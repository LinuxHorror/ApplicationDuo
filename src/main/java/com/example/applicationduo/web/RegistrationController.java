package com.example.applicationduo.web;

import com.example.applicationduo.dto.UserCreationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    public static final String REG_PAGE = "registration";
    @GetMapping
    public ModelAndView regPage(@ModelAttribute(name="newUser") UserCreationDto user){
        var modelAndView = new ModelAndView(REG_PAGE);
        return modelAndView;
    }




}
