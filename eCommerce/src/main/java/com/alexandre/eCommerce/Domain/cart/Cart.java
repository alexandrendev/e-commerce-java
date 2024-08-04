package com.alexandre.eCommerce.Domain.cart;

import com.alexandre.eCommerce.Domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "product_cart")
public class Cart {
    @Id
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "product_cart",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @Autowired
    public Cart(List<Product> products) {
        this.products = products;
    }
}
