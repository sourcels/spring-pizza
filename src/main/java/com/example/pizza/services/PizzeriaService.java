package com.example.pizza.services;

import com.example.pizza.models.PizzeriaModel;
import com.example.pizza.repositories.PizzeriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PizzeriaService {
    private final PizzeriaRepository productRepository;

    public List<PizzeriaModel> list(String name) {
        if (name != null) return productRepository.findByName(name);
        return productRepository.findAll();
    }

    public boolean saveProductPizza(PizzeriaModel product) {
        if (product != null && product.getName() != "") {
            log.info("Saving new {}", product);
            productRepository.save(product);
            return true;
        }
        throw new IllegalArgumentException("Product object or its name is empty");
    }

    public void deleteProductPizza(Long id) {
        productRepository.deleteById(id);
    }

    public void getProductPizza(Long id) {
        productRepository.findById(id).orElse(null);
    }
}
