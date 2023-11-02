package com.example.pizza.services;

import com.example.pizza.models.ProductPizza;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<ProductPizza> products = new ArrayList<>();
    private long ID = 0;

    {
        products.add(new ProductPizza(++ID,"DodoPizza", 987654321L ,"pizzastr. 82, Berlin 12305"));
        products.add(new ProductPizza(++ID,"Gobos", 987654321L,"pizzastr. 83, Berlin 12305"));
        products.add(new ProductPizza(++ID,"Papajoe",987654321L ,"pizzastr. 84, Berlin 12305"));
    }

    public List<ProductPizza> list() { return products;}

    public void saveProductPizza(ProductPizza product) {
        product.setId(++ID);
        products.add(product);
    }

    public void deleteProductPizza(Long id) {
        products.removeIf(productPizza -> productPizza.getId().equals(id));
    }
}
