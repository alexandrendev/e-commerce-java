package com.alexandre.eCommerce.Domain.cart;

import com.alexandre.eCommerce.Domain.product.Product;
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
public class Cart {
    private Long id;
    private List<Product> products;

    @Autowired
    public Cart(List<Product> products) {
        this.products = products;
    }
}
