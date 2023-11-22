package com.example.pizza.services;

import com.example.pizza.models.MealModel;
import com.example.pizza.models.PizzeriaMealsModel;
import com.example.pizza.models.PizzeriaModel;
import com.example.pizza.repositories.MealRepository;
import com.example.pizza.repositories.PizzeriaRepository;
import com.example.pizza.repositories.PizzeriaToMealsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PizzeriaService {
    private final PizzeriaRepository pizzeriaRepository;
    private final MealRepository mealRepository;
    private final PizzeriaToMealsRepository pizzeriaToMealsRepository;

    public List<PizzeriaModel> listPizzerias(String name) {
        if (name != null) return pizzeriaRepository.findByName(name);
        return pizzeriaRepository.findAll();
    }

    public PizzeriaModel getPizzeriaById(Long id) {
        return pizzeriaRepository.findById(id).orElse(null);
    }

    public void savePizzeria(PizzeriaModel pizzeria) {
        if (pizzeria != null && pizzeria.getName() != "") {
            log.info("Saving new {}", pizzeria);
            pizzeriaRepository.save(pizzeria);
        }
        else {
            throw new IllegalArgumentException("Product object or its name is empty");
        }
    }

    public void addMealsToPizzeria(Long pizzeria_id, Long meal_id) {
        PizzeriaModel pizzeria = pizzeriaRepository.getById(pizzeria_id);
        MealModel meal = mealRepository.getById(meal_id);

        PizzeriaMealsModel pizzeriaToMeals = new PizzeriaMealsModel();
        pizzeriaToMeals.setPizzeria(pizzeria);
        pizzeriaToMeals.setMeal(meal);

        pizzeriaToMealsRepository.save(pizzeriaToMeals);
        log.info("Saving new {}", pizzeriaToMeals);
    }

    public void deletePizzeria(Long id) {pizzeriaRepository.deleteById(id);
    }
}
