package com.example.pizza.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPizza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id")
    private Long id;
    @Column(name= "name")
    private String name;
    @Column(name= "phone")
    private Long phone;
    @Column(name= "address")
    private String address;
    @Column(name= "description", columnDefinition = "text")
    private String description;
}
