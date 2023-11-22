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
        model.addAttribute("pizzerias", pizzeriaService.listPizzerias(name));
        return "pizzerias";
    }

    @GetMapping("/{pizzeria_id}")
    public String getPizzeriaById(@PathVariable Long pizzeria_id, Model model) {
        PizzeriaModel pizzeria = pizzeriaService.getPizzeriaById(pizzeria_id);
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
    public String addMealsToPizzeria(@RequestParam Long pizzeria_id, @RequestParam Long meal_id) {
        pizzeriaService.addMealsToPizzeria(pizzeria_id, meal_id);
        return String.format("redirect:/pizzerias/%d", pizzeria_id);
    }

    @PostMapping("/delete/{pizzeria_id}")
    public String deletePizzeria(@PathVariable Long pizzeria_id) {
        pizzeriaService.deletePizzeria(pizzeria_id);
        return "redirect:/";
    }
}
