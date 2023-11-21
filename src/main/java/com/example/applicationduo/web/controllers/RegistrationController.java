package com.example.applicationduo.web.controllers;

import com.example.applicationduo.service.UserService;
import com.example.applicationduo.dto.UserCreationDto;
import com.example.applicationduo.mappers.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService service;
    private final UserMapper userMapper;


    @GetMapping
    public ModelAndView regPage(@ModelAttribute("newUser") UserCreationDto user) {
        var modelAndView = new ModelAndView("registration");
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView validPage(@Valid @ModelAttribute("newUser") UserCreationDto user,
                                  BindingResult bindingResult,
                                  @RequestParam(name = "checkPassword") String checkPass) {
        var modelAndView = new ModelAndView("registration");
        if (!bindingResult.hasFieldErrors()) {
            if (user.getPassword().equals(checkPass)) {
                service.save(userMapper.toEntity(user));
                return new ModelAndView("redirect:/enter");
            }else {
                modelAndView.addObject("check", false);
            }
        } else {
            modelAndView.addObject("check", false);
        }
        return modelAndView;
    }


}
