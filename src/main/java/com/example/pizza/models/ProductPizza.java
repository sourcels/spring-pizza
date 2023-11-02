package com.example.pizza.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductPizza {
    private Long id;
    private String name;
    private Long phone;
    private String address;
}
