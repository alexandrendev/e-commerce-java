package com.alexandre.eCommerce.DTO;

import com.alexandre.eCommerce.Domain.Category;
import com.alexandre.eCommerce.Domain.Product;

import java.math.BigDecimal;

public record ProductDTO(String name, String description, BigDecimal price, Category category) {
    public Product toProduct(){
        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .category(category)
                .build();
    }
}
