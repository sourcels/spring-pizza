package com.example.pizza.controllers;

import com.example.pizza.models.PizzeriaModel;
import com.example.pizza.services.PizzeriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PizzaController {
    private final PizzeriaService pizzeriaService;

    @GetMapping("/")
    public String products(@RequestParam(name = "name", required = false) String name,Model model) {
        model.addAttribute("pizzerias", pizzeriaService.listPizzerias(name));
        return "pizzerias";
    }

    @PostMapping("/product/create")
    public String createProductPizza(PizzeriaModel product) {
        pizzeriaService.savePizzeria(product);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProductPizza(@PathVariable Long id) {
        pizzeriaService.deletePizzeria(id);
        return "redirect:/";
    }

    @GetMapping("/error")
    public String handleError(Model model) {
        // Обработка ошибок и отображение информации об ошибке на странице
        return "redirect:/error";
    }
}
