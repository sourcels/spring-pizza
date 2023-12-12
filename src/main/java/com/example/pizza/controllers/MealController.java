package com.example.pizza.controllers;

import com.example.pizza.models.MealModel;
import com.example.pizza.services.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/meals")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @GetMapping
    public String meals(Model model) {
        model.addAttribute("meals", mealService.getAllMeals());
        return "meals";
    }

    @GetMapping("/{mealId}")
    public String getMealById(@PathVariable Long mealId, Model model) {
        Optional<MealModel> optionalMeal = mealService.getMealById(mealId);
        if (optionalMeal.isPresent()) {
            model.addAttribute("meal", optionalMeal.get());
            return "meal_detail";
        } else {
            return "redirect:/meals";
        }
    }

    @PostMapping("/create")
    public String createMeal(@ModelAttribute MealModel meal) {
        mealService.saveMeal(meal);
        return "redirect:/meals";
    }

    @PostMapping("/edit/{mealId}")
    public String editMeal(@PathVariable Long mealId, MealModel meal) {
        mealService.editMeal(mealId, meal);
        return String.format("redirect:/meals/%d", mealId);
    }

    @PostMapping("/delete")
    public String deleteMeal(@RequestParam Long mealId) {
        mealService.removeAllPizzeriasFromMeal(mealId);
        mealService.deleteMeal(mealId);
        return "redirect:/meals";
    }
}