package com.example.pizza.controllers;

import com.example.pizza.models.MealModel;
import com.example.pizza.models.PizzeriaModel;
import com.example.pizza.services.MealService;
import com.example.pizza.services.PizzeriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/pizzerias")
@RequiredArgsConstructor
public class PizzeriaController {
    private final PizzeriaService pizzeriaService;
    private final MealService mealService;

    @GetMapping
    public String products(@RequestParam(name = "name", required = false) String name,Model model) {
        model.addAttribute("pizzerias", pizzeriaService.getAllPizzerias(name));
        return "pizzerias";
    }

    @GetMapping("/{pizzeriaId}")
    public String getPizzeriaById(@PathVariable Long pizzeriaId, Model model) {
        PizzeriaModel pizzeria = pizzeriaService.getPizzeriaById(pizzeriaId);
        List<MealModel> allMeals = mealService.getAllMeals();
        if (pizzeria != null) {
            model.addAttribute("pizzeria", pizzeria);
            model.addAttribute("meals", allMeals);
            return "pizzeria_detail";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/create")
    public String createPizzeria(PizzeriaModel pizzeria) {
        pizzeriaService.savePizzeria(pizzeria);
        return "redirect:/";
    }

    @PostMapping("/addMeal")
    public String addMealsToPizzeria(@RequestParam Long pizzeriaId, @RequestParam Long mealId) {
        pizzeriaService.addMealToPizzeria(pizzeriaId, mealId);
        return String.format("redirect:/pizzerias/%d", pizzeriaId);
    }

    @PostMapping("/delete/{pizzeriaId}")
    public String deletePizzeria(@PathVariable Long pizzeriaId) {
        pizzeriaService.deletePizzeria(pizzeriaId);
        return "redirect:/";
    }
}
