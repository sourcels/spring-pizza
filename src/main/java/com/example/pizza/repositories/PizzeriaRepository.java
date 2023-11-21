package com.example.pizza.repositories;

import com.example.pizza.models.MealModel;
import com.example.pizza.models.PizzeriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface PizzeriaRepository extends JpaRepository<PizzeriaModel, Long> {
    List<PizzeriaModel> findByName(String name);

    @Query("SELECT p.pizzeriaMeals FROM PizzeriaModel p WHERE p.id = :pizzeriaId")
    Set<MealModel> findPizzeriaMeals(@Param("pizzeriaId") Long pizzeriaId);
}
