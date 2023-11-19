package com.example.pizza.repositories;

import com.example.pizza.models.PizzeriaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzeriaRepository extends JpaRepository<PizzeriaModel, Long> {
    List<PizzeriaModel> findByName(String name);
}
