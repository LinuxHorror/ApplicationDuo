package com.example.applicationduo.entity;

import com.example.applicationduo.dto.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class CartEntity {
    @Id
    private Integer idInCart;

    private String productTitle;

    private String description;

    private Float price;

    @ManyToOne
    private UserEntity user;

}
