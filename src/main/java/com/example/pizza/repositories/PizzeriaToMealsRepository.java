package com.example.pizza.repositories;

import com.example.pizza.models.MealModel;
import com.example.pizza.models.PizzeriaMeals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzeriaToMealsRepository extends JpaRepository<PizzeriaMeals, Long> {
}