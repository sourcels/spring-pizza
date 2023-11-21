package com.example.pizza.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    @GetMapping
    public String redirect() {
        return "redirect:/pizzerias";
    }
}