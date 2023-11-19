package com.example.pizza.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="pizzerias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzeriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id")
    private Long id;
    @Column(name= "name")
    private String name;
    @Column(name= "phone")
    private String phone;
    @Column(name= "address")
    private String address;
    @Column(name= "description", columnDefinition = "text")
    private String description;
}
