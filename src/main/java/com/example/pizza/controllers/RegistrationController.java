package com.example.pizza.controllers;

import com.example.pizza.models.Role;
import com.example.pizza.models.User;
import com.example.pizza.repositories.UserRepository;
import com.example.pizza.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if (userService.createUser(user)) {
            return "redirect:/login";
        }
        model.addAttribute("message", "User exists!");
        return "registration";
    }
}