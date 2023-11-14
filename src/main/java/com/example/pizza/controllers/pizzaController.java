package com.example.pizza.controllers;

import com.example.pizza.models.ProductPizza;
import com.example.pizza.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class pizzaController {
    private final ProductService productService;

    @GetMapping("/")
    public String products(@RequestParam(name = "name", required = false) String name,Model model) {
        model.addAttribute("products", productService.list(name));
        return "products";
    }

    @PostMapping("/product/create")
    public String createProductPizza(ProductPizza product) {
        productService.saveProductPizza(product);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProductPizza(@PathVariable Long id) {
        productService.deleteProductPizza(id);
        return "redirect:/";
    }
}
