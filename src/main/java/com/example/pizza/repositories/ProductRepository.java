package com.example.pizza.repositories;

import com.example.pizza.models.ProductPizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductPizza, Long> {
    List<ProductPizza> findByName(String name);
}
