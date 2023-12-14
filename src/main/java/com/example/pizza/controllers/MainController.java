package com.example.pizza.controllers;

import com.example.pizza.models.MealModel;
import com.example.pizza.models.PizzeriaModel;
import com.example.pizza.services.MealService;
import com.example.pizza.services.PizzeriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
@RequiredArgsConstructor
public class MainController {
    private final PizzeriaService pizzeriaService;
    private final MealService mealService;

    @GetMapping
    @Tag(name = "Main page", description = "Return index.ftlh")
    public String redirect(@RequestParam(name = "name", required = false) String name, Model model) {
        List<PizzeriaModel> allPizzerias = pizzeriaService.getAllPizzerias(name);
        Set<MealModel> allMeals = new HashSet<>();
        if (name == null || name.isBlank()) {
            allMeals = mealService.getAllMeals();
        } else {
            if (allPizzerias.size() > 0) {
                allMeals = pizzeriaService.getAllMealsInPizzeria(allPizzerias.get(0).getPizzeria_id());
            }
        }
        model.addAttribute("pizzerias", allPizzerias);
        model.addAttribute("meals", allMeals);
        return "index";
    }
}