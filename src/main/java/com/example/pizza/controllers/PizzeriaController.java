package com.example.pizza.controllers;

import com.example.pizza.models.PizzeriaModel;
import com.example.pizza.services.PizzeriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/pizzerias")
@RequiredArgsConstructor
public class PizzeriaController {
    private final PizzeriaService pizzeriaService;

    @GetMapping
    public String products(@RequestParam(name = "name", required = false) String name,Model model) {
        model.addAttribute("pizzerias", pizzeriaService.listPizzerias(name));
        return "pizzerias";
    }

    @GetMapping("/{pizzeria_id}")
    public String getMealById(@PathVariable Long pizzeria_id, Model model) {
        PizzeriaModel pizzeria = pizzeriaService.getPizzeriaById(pizzeria_id);
        if (pizzeria != null) {
            model.addAttribute("pizzeria", pizzeria);
            return "redirect:/pizzeria";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/create")
    public String createProductPizza(PizzeriaModel product) {
        pizzeriaService.savePizzeria(product);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteProductPizza(@PathVariable Long id) {
        pizzeriaService.deletePizzeria(id);
        return "redirect:/";
    }

    @GetMapping("/error")
    public String handleError(Model model) {
        return "redirect:/error";
    }
}
