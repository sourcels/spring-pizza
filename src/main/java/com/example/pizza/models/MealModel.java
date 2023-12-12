package com.example.pizza.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="meals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Long meal_id;

    @Column(name = "name", length = 64, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "price")
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", length = 64)
    private MealCategory category;

    @ManyToMany(mappedBy = "meals")
    private Set<PizzeriaModel> pizzerias = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(meal_id);
    }
}