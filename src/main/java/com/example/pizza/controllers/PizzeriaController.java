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

import java.util.List;
import java.util.Set;


@Controller
@RequestMapping(value = "/pizzerias", method = RequestMethod.GET)
@RequiredArgsConstructor
public class PizzeriaController {
    private final PizzeriaService pizzeriaService;
    private final MealService mealService;

    @GetMapping
    @Tag(name = "Pizzerias page", description = "Return pizzerias.ftlh")
    public String products(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("pizzerias", pizzeriaService.getAllPizzerias(name));
        return "pizzerias";
    }

    @GetMapping("/{pizzeriaId}")
    @Tag(name = "Pizzeria page", description = "Return pizzeria_detail.ftlh")
    public String getPizzeriaById(@PathVariable Long pizzeriaId, Model model) {
        PizzeriaModel pizzeria = pizzeriaService.getPizzeriaById(pizzeriaId);
        Set<MealModel> allPizzeriaMeals = pizzeriaService.getAllMealsInPizzeria(pizzeriaId);
        List<MealModel> allMealsNotInPizzeria = pizzeriaService.getMealsNotInPizzeria(pizzeriaId);
        Set<MealModel> allMeals = mealService.getAllMeals();
        if (pizzeria != null) {
            model.addAttribute("pizzeria", pizzeria);
            model.addAttribute("meals", allMeals);
            model.addAttribute("mealsNotInPizzeria", allMealsNotInPizzeria);
            model.addAttribute("pizzeriaMeals", allPizzeriaMeals);
            return "pizzeria_detail";
        } else {
            return "redirect:/pizzerias";
        }
    }

    @PostMapping("/create")
    @Tag(name = "Create pizzeria", description = "Save new pizzeria in database")
    public String createPizzeria(PizzeriaModel pizzeria) {
        pizzeriaService.savePizzeria(pizzeria);
        return "redirect:/pizzerias";
    }

    @PostMapping("/edit/{pizzeriaId}")
    @Tag(name = "Edit pizzeria", description = "Edit existing pizzeria in database")
    public String editPizzeria(@PathVariable Long pizzeriaId, PizzeriaModel pizzeria) {
        pizzeriaService.editPizzeria(pizzeriaId, pizzeria);
        return String.format("redirect:/pizzerias/%d", pizzeriaId);
    }

    @PostMapping("/addMeal")
    @Tag(name = "Add meal to pizzeria", description = "Add meal to pizzeria")
    public String addMealsToPizzeria(@RequestParam Long pizzeriaId, @RequestParam Long mealId) {
        pizzeriaService.addMealToPizzeria(pizzeriaId, mealId);
        return String.format("redirect:/pizzerias/%d", pizzeriaId);
    }

    @PostMapping("/deleteMeal")
    @Tag(name = "Remove meal from pizzeria", description = "Remove meal from pizzeria_meal database")
    public String deletePizzeriaMeal(@RequestParam Long mealId, @RequestParam Long pizzeriaId) {
        pizzeriaService.removePizzeriaMeal(pizzeriaId, mealId);
        return String.format("redirect:/pizzerias/%d", pizzeriaId);
    }

    @PostMapping("/delete")
    @Tag(name = "Delete pizzeria", description = "Delete existing pizzeria from database")
    public String deletePizzeria(@RequestParam Long pizzeriaId) {
        pizzeriaService.removePizzeriaMeals(pizzeriaId);
        pizzeriaService.deletePizzeria(pizzeriaId);
        return "redirect:/pizzerias";
    }
}