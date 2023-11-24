package com.example.applicationduo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String productTitle;

    private String description;

    private Float price;

    private Integer count;

    @UpdateTimestamp
    private Date dateOfUpdate;

    @CreationTimestamp
    @Temporal(value = TemporalType.DATE)
    private Date dateOfCreation;

    @Version
    private Integer version;
}
