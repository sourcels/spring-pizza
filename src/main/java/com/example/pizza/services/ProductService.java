package com.example.pizza.services;

import com.example.pizza.models.ProductPizza;
import com.example.pizza.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductPizza> list(String name) {
        if (name != null) productRepository.findByName(name);
        return productRepository.findAll();
    }

    public void saveProductPizza(ProductPizza product) {
        if (product != null && !product.getName().equals(null)) {
            log.info("Saving new {}", product);
            productRepository.save(product);
        }
    }

    public void deleteProductPizza(Long id) {
        productRepository.deleteById(id);
    }

    public void getProductPizza(Long id) {
        productRepository.findById(id).orElse(null);
    }
}
