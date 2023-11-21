package com.example.pizza.services;

import com.example.pizza.models.MealModel;
import com.example.pizza.models.PizzeriaModel;
import com.example.pizza.repositories.MealRepository;
import com.example.pizza.repositories.PizzeriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;

    public List<MealModel> listMeals(String name) {
        if (name != null) return mealRepository.findByName(name);
        return mealRepository.findAll();
    }

    public List<MealModel> getAllMeals() {
        return mealRepository.findAll();
    }

    public Optional<MealModel> getMealById(Long mealId) {
        return mealRepository.findById(mealId);
    }

    public MealModel saveMeal(MealModel meal) {
        return mealRepository.save(meal);
    }

    public void deleteMeal(Long mealId) {
        mealRepository.deleteById(mealId);
    }
}