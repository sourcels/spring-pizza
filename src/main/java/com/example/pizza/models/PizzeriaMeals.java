package com.example.pizza.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pizzerias_2_meal", uniqueConstraints = @UniqueConstraint(columnNames = {"pizzeria_id", "meal_id"}))
public class PizzeriaMeals {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pizzeria_id")
    private PizzeriaModel pizzeria;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private MealModel meal;
}