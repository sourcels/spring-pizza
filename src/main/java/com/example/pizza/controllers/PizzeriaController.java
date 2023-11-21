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
            model.addAttribute("allMeals", allMeals);
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

    @PostMapping("/delete/{pizzeria_id}")
    public String deletePizzeria(@PathVariable Long pizzeria_id) {
        pizzeriaService.deletePizzeria(pizzeria_id);
        return "redirect:/";
    }

    @PostMapping("/addMeals/{pizzeria_id}")
    public String addMealsToPizzeria(@RequestParam Long pizzeria_id, @RequestParam List<Long> meals) {
        pizzeriaService.addMealsToPizzeria(pizzeria_id, meals);
        return String.format("redirect:/pizzeria/%d", pizzeria_id);
    }

    @GetMapping("/error")
    public String handleError(Model model) {
        return "redirect:/error";
    }
}
