package com.example.applicationduo.exceptions.handlers;

import com.example.applicationduo.exceptions.UserNotRegisteredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(UserNotRegisteredException.class)
    public String catchMissingUser(UserNotRegisteredException exception){
        return "redirect:/login";
    }
}
