package com.example.applicationduo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String role;

    private String username;

    private String password;

    @CreationTimestamp
    private Date dateOfCreation;

    @UpdateTimestamp
    private Date dateOfUpdate;

    @Version
    private Integer version;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<CartEntity> cart;


    public void addToCart(CartEntity cartEntity) {
        if (cart == null) {
            cart = new ArrayList<>();
        }
        if (!cart.isEmpty()) {
            for (CartEntity entity : cart) {
                if (entity.getProductId().equals(cartEntity.getProductId())) {
                    entity.setCount(cartEntity.getCount());
                    return;
                }
            }
        }
        cart.add(cartEntity);
        cartEntity.setUser(this);
        //TODO CHECK THAT THIS PRODUCT ALREADY EXISTS, THEN DO UPDATE ( REMOVE -> ADD)
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
