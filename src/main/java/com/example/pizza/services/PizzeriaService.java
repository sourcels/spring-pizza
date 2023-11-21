package com.example.pizza.services;

import com.example.pizza.models.PizzeriaModel;
import com.example.pizza.repositories.PizzeriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PizzeriaService {
    private final PizzeriaRepository pizzeriaRepository;

    public List<PizzeriaModel> listPizzerias(String name) {
        if (name != null) return pizzeriaRepository.findByName(name);
        return pizzeriaRepository.findAll();
    }

    public PizzeriaModel getPizzeriaById(Long id) {
        return pizzeriaRepository.findById(id).orElse(null);
    }

    public void savePizzeria(PizzeriaModel product) {
        if (product != null && product.getName() != "") {
            log.info("Saving new {}", product);
            pizzeriaRepository.save(product);
        }
        else {
            throw new IllegalArgumentException("Product object or its name is empty");
        }
    }

    public void deletePizzeria(Long id) {
        pizzeriaRepository.deleteById(id);
    }
}
