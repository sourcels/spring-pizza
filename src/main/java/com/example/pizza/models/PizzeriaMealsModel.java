package com.example.pizza.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pizzerias_2_meal", uniqueConstraints = @UniqueConstraint(columnNames = {"pizzeria_id", "meal_id"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzeriaMealsModel {
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