package com.example.pizza.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name="pizzerias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzeriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "pizzeria_id")
    private Long pizzeria_id;

    @Column(name= "name", length=64)
    private String name;

    @Column(name= "phone", length=12)
    private String phone;

    @Column(name= "address", length=128)
    private String address;

    @Column(name= "description", columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
    private Set<MealModel> meals;
}