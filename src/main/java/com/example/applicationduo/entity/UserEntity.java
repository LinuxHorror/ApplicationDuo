package com.example.applicationduo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    private String email;

    private String password;

    @CreationTimestamp
    private Date dateOfCreation;

    @UpdateTimestamp
    private Date dateOfUpdate;

    @Version
    private Integer version;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<CartEntity> cart;

    public void addToCart(CartEntity cartEntity){
        if(cart == null){
            cart = new ArrayList<>();
        }
        cart.add(cartEntity);
        cartEntity.setUser(this);
        //TODO CHECK THAT THIS PRODUCT ALREADY EXISTS, THEN DO UPDATE ( REMOVE -> ADD)
    }
}
