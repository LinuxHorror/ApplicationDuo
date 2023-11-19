package com.example.applicationduo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class ProductEntity {
    @Id
    private UUID id;

    private String name;
    private String description;
    private Float price;

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    public ProductEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductEntity setPrice(Float price) {
        this.price = price;
        return this;
    }
}
