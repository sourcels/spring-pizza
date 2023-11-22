package com.example.pizza.repositories;

import com.example.pizza.models.PizzeriaMealsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzeriaToMealsRepository extends JpaRepository<PizzeriaMealsModel, Long> {
}