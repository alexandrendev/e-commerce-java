package com.alexandre.eCommerce.Domain.user;


import com.alexandre.eCommerce.Domain.cart.Cart;
import com.alexandre.eCommerce.Domain.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(exclude = "cart")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;
    @Column(name = "role", nullable = false)
    @JsonIgnore
    private UserRole role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserAddress> addresses;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Email> emails;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Order> orders;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Cart cart;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(role.getRole().equals("admin")) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
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
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public User(String name, String username, String password, UserRole role){
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
