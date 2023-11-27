package com.example.pizza.services;

import com.example.pizza.models.MealModel;
import com.example.pizza.models.PizzeriaModel;
import com.example.pizza.repositories.MealRepository;
import com.example.pizza.repositories.PizzeriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class PizzeriaService {
    private final PizzeriaRepository pizzeriaRepository;
    private final MealRepository mealRepository;

    public List<PizzeriaModel> getAllPizzerias(String name) {
        if (name != null) return pizzeriaRepository.findByName(name);
        return pizzeriaRepository.findAll();
    }

    public PizzeriaModel getPizzeriaById(Long pizzeriaId) {
        return pizzeriaRepository.findById(pizzeriaId).orElse(null);
    }

    public void savePizzeria(PizzeriaModel pizzeria) {
        if (pizzeria != null && pizzeria.getName() != "") {
            log.info("Saving new {}", pizzeria);
            pizzeriaRepository.save(pizzeria);
        }
        else {
            throw new IllegalArgumentException("Pizzeria object or its name is empty");
        }
    }

    public Set<MealModel> getAllMealsInPizzeria(Long pizzeriaId) {
        Optional<PizzeriaModel> optionalPizzeria = pizzeriaRepository.findById(pizzeriaId);

        if (optionalPizzeria.isPresent()) {
            PizzeriaModel pizzeria = optionalPizzeria.get();
            return pizzeria.getMeals();
        }
        return Set.of();
    }

    public void addMealToPizzeria(Long pizzeriaId, Long mealId) {
        Optional<MealModel> optionalMeal = mealRepository.findById(mealId);
        Optional<PizzeriaModel> optionalPizzeria = pizzeriaRepository.findById(pizzeriaId);

        if (optionalMeal.isPresent() && optionalPizzeria.isPresent()) {
            MealModel meal = optionalMeal.get();
            PizzeriaModel pizzeria = optionalPizzeria.get();

            pizzeria.getMeals().add(meal);
            pizzeriaRepository.save(pizzeria);

            meal.getPizzerias().add(pizzeria);
            mealRepository.save(meal);
        } else {
            throw new IllegalArgumentException("Product name is empty or doesn't exists");
        }
    }

    public void deletePizzeria(Long id) {pizzeriaRepository.deleteById(id);
    }
}
