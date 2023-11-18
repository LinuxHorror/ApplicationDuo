package com.example.applicationduo.web;


import com.example.applicationduo.dto.UserCreationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/enter")
public class EnterPageController {
    @GetMapping
    public ModelAndView getMainPage(@ModelAttribute("newUser") UserCreationDto dto){
        return new ModelAndView("enterPage");
    }
}
