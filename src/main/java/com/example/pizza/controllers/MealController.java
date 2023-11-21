package com.example.pizza.controllers;

import com.example.pizza.models.MealModel;
import com.example.pizza.services.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping
    public String meals(Model model) {
        List<MealModel> meals = mealService.getAllMeals();
        model.addAttribute("meals", meals);
        return "meals/list";
    }

    @PostMapping("/create")
    public String createMeal(@ModelAttribute MealModel meal) {
        mealService.saveMeal(meal);
        return "redirect:/meals";
    }

    @GetMapping("/delete/{id}")
    public String deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return "redirect:/meals";
    }

    @GetMapping("/{id}")
    public String getMealById(@PathVariable Long id, Model model) {
        Optional<MealModel> optionalMeal = mealService.getMealById(id);
        if (optionalMeal.isPresent()) {
            model.addAttribute("meal", optionalMeal.get());
            return "meals/details";
        } else {
            return "redirect:/meals";
        }
    }
}