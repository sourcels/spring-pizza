package com.example.pizza.repositories;

import com.example.pizza.models.MealModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<MealModel, Long> {
    List<MealModel> findByName(String name);
}