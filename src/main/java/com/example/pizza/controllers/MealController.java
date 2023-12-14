package com.example.pizza.controllers;

import com.example.pizza.models.MealModel;
import com.example.pizza.services.MealService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/meals", method = RequestMethod.GET)
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @GetMapping
    @Tag(name = "Meals page", description = "Return meals.ftlh")
    public String meals(Model model) {
        model.addAttribute("meals", mealService.getAllMeals());
        return "meals";
    }

    @GetMapping("/{mealId}")
    @Tag(name = "Meal page", description = "Return meal_detail.ftlh")
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
    @Tag(name = "Create meal", description = "Save new meal in database")
    public String createMeal(@ModelAttribute MealModel meal) {
        mealService.saveMeal(meal);
        return "redirect:/meals";
    }

    @PostMapping("/edit/{mealId}")
    @Tag(name = "Edit meal", description = "Edit existing meal in database")
    public String editMeal(@PathVariable Long mealId, MealModel meal) {
        mealService.editMeal(mealId, meal);
        return String.format("redirect:/meals/%d", mealId);
    }

    @PostMapping("/delete")
    @Tag(name = "Delete meal", description = "Delete existing meal in database")
    public String deleteMeal(@RequestParam Long mealId) {
        mealService.removeAllPizzeriasFromMeal(mealId);
        mealService.deleteMeal(mealId);
        return "redirect:/meals";
    }
}