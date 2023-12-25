package com.example.applicationduo.web.controllers;


import com.example.applicationduo.dto.UserCreationDto;
import com.example.applicationduo.entity.UserEntity;
import com.example.applicationduo.model.CurrentUser;
import com.example.applicationduo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RequiredArgsConstructor

@Controller
@RequestMapping("/login")
public class LoginPageController {
    private final UserService service;

    @GetMapping
    public ModelAndView getMainPage(@ModelAttribute("newUser") UserCreationDto dto) {
        return new ModelAndView("loginPage");
    }

  /*  @PostMapping("/submit")
    public ModelAndView enterData(@Valid @ModelAttribute("newUser") UserCreationDto dto,
                                  BindingResult result) {
        if (!result.hasFieldErrors()) {
            //TODO SAVE THIS USER TO SESSION OR COOKIE
            if (service.isExistsInDb(dto)) {
                var model = new ModelAndView("loginPage");
                model.addObject("notFound", false);
                return model;
            }
        }
        return new ModelAndView("loginPage");
    }*/
}
