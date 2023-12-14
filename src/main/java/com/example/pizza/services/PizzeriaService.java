package com.example.pizza.services;

import com.example.pizza.models.MealModel;
import com.example.pizza.models.PizzeriaModel;
import com.example.pizza.repositories.MealRepository;
import com.example.pizza.repositories.PizzeriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
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

    @Transactional
    public void editPizzeria(Long pizzeriaId, PizzeriaModel updatedPizzeria) {
        Optional<PizzeriaModel> optionalPizzeria = pizzeriaRepository.findById(pizzeriaId);

        if (optionalPizzeria.isPresent()) {
            PizzeriaModel existingPizzeria = optionalPizzeria.get();

            BeanUtils.copyProperties(updatedPizzeria, existingPizzeria, getNullPropertyNames(updatedPizzeria));

            handleMeals(existingPizzeria, updatedPizzeria.getMeals());

            log.info("Saving edited {}", existingPizzeria);
            pizzeriaRepository.save(existingPizzeria);
        }
    }

    private void handleMeals(PizzeriaModel existingPizzeria, Set<MealModel> updatedMeals) {
        existingPizzeria.getMeals().clear();
        existingPizzeria.getMeals().addAll(updatedMeals);
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public Set<MealModel> getAllMealsInPizzeria(Long pizzeriaId) {
        Optional<PizzeriaModel> optionalPizzeria = pizzeriaRepository.findById(pizzeriaId);

        if (optionalPizzeria.isPresent()) {
            PizzeriaModel pizzeria = optionalPizzeria.get();
            return pizzeria.getMeals();
        }
        return Set.of();
    }

    public List<MealModel> getMealsNotInPizzeria(Long pizzeriaId) {
        List<MealModel> allMeals = mealRepository.findAll();
        Set<MealModel> mealsInPizzeria = getAllMealsInPizzeria(pizzeriaId);

        List<MealModel> mealsNotInPizzeria = new ArrayList<>(allMeals);
        mealsNotInPizzeria.removeAll(mealsInPizzeria);

        return mealsNotInPizzeria;
    }

    public void addMealToPizzeria(Long pizzeriaId, Long mealId) {
        Optional<PizzeriaModel> optionalPizzeria = pizzeriaRepository.findById(pizzeriaId);
        Optional<MealModel> optionalMeal = mealRepository.findById(mealId);

        if (optionalPizzeria.isPresent() && optionalMeal.isPresent()) {
            PizzeriaModel pizzeria = optionalPizzeria.get();
            MealModel meal = optionalMeal.get();

            pizzeria.getMeals().add(meal);
            meal.getPizzerias().add(pizzeria);

            log.info("Added meal to {}", pizzeria);
            pizzeriaRepository.save(pizzeria);
            mealRepository.save(meal);
        } else {
            System.out.println("Пиццерия или блюдо не найдены");
        }
    }

    @Transactional
    public void removePizzeriaMeal(Long pizzeriaId, Long mealId) {
        Optional<PizzeriaModel> optionalPizza = pizzeriaRepository.findById(pizzeriaId);
        Optional<MealModel> optionalMeal = mealRepository.findById(mealId);

        if (optionalPizza.isPresent() && optionalMeal.isPresent()) {
            PizzeriaModel pizza = optionalPizza.get();
            MealModel meal = optionalMeal.get();

            log.info("Removing {} from pizzeria {}", meal, pizza);
            pizza.getMeals().remove(meal);
            pizzeriaRepository.save(pizza);
        }
    }

    public void removePizzeriaMeals(Long pizzeriaId) {
        Optional<PizzeriaModel> optionalPizzeria = pizzeriaRepository.findById(pizzeriaId);

        if (optionalPizzeria.isPresent()) {
            PizzeriaModel pizzeria = optionalPizzeria.get();

            Set<MealModel> mealsCopy = new HashSet<>(pizzeria.getMeals());

            for (MealModel meal : mealsCopy) {
                removePizzeriaMeal(pizzeriaId, meal.getMeal_id());
            }
        }
    }

    public void deletePizzeria(Long pizzeriaId) {
        log.info("Removing pizzeria by ID:{}", pizzeriaId);
        pizzeriaRepository.deleteById(pizzeriaId);
    }
}
