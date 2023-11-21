package com.example.pizza.repositories;

import com.example.pizza.models.PizzeriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzeriaRepository extends JpaRepository<PizzeriaModel, Long> {
    List<PizzeriaModel> findByName(String name);
}