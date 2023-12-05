package com.example.pizza.services;

import com.example.pizza.models.MealModel;
import com.example.pizza.models.PizzeriaModel;
import com.example.pizza.repositories.MealRepository;
import com.example.pizza.repositories.PizzeriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;
    private final PizzeriaRepository pizzeriaRepository;

    public Set<MealModel> getAllMeals() {
        List<MealModel> mealsList = mealRepository.findAll();
        Set<MealModel> mealsSet = new HashSet<>(mealsList);
        return mealsSet;
    }

    public Optional<MealModel> getMealById(Long mealId) {
        return mealRepository.findById(mealId);
    }

    public void removePizzeriaFromMeal(Long mealId, Long pizzeriaId) {
        Optional<MealModel> optionalMeal = mealRepository.findById(mealId);
        Optional<PizzeriaModel> optionalPizzeria = pizzeriaRepository.findById(pizzeriaId);

        if (optionalMeal.isPresent() && optionalPizzeria.isPresent()) {
            MealModel meal = optionalMeal.get();
            PizzeriaModel pizzeria = optionalPizzeria.get();

            meal.getPizzerias().remove(pizzeria);
            pizzeria.getMeals().remove(meal);

            mealRepository.save(meal);
            pizzeriaRepository.save(pizzeria);
        }
    }

    public void removeAllPizzeriasFromMeal(Long mealId) {
        Optional<MealModel> optionalMeal = mealRepository.findById(mealId);

        if (optionalMeal.isPresent()) {
            MealModel meal = optionalMeal.get();

            Set<PizzeriaModel> pizzeriasCopy = new HashSet<>(meal.getPizzerias());

            for (PizzeriaModel pizzeria : pizzeriasCopy) {
                removePizzeriaFromMeal(mealId, pizzeria.getPizzeria_id());
            }
        }
    }

    public MealModel saveMeal(MealModel meal) {
        return mealRepository.save(meal);
    }

    public void deleteMeal(Long mealId) {
        mealRepository.deleteById(mealId);
    }
}