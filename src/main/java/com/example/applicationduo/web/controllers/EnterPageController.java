package com.example.applicationduo.web.controllers;


import com.example.applicationduo.dao.UserService;
import com.example.applicationduo.dto.UserCreationDto;
import com.example.applicationduo.mappers.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor

@Controller
@RequestMapping("/enter")
public class EnterPageController {

    private final UserMapper userMapper;
    private final UserService service;
    @GetMapping
    public ModelAndView getMainPage(@ModelAttribute("newUser") UserCreationDto dto){
        return new ModelAndView("enterPage");
    }

    @PostMapping("/submit")
    public ModelAndView enterData(@Valid @ModelAttribute("newUser") UserCreationDto dto, BindingResult result){
        if(!result.hasFieldErrors()){
            //TODO SAVE THIS USER TO SESSION OR COOKIE
            if(!service.isExistsInDb(dto)){
                var model = new ModelAndView("enterPage");
                model.addObject("notFound", false);
                return model;
            }
        }
        return new ModelAndView("redirect:/store");
    }
}
