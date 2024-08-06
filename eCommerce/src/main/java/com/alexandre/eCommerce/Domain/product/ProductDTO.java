package com.alexandre.eCommerce.Domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record ProductDTO(

        @NotBlank(message = "Product name cannot be empty")
        @NotNull(message = "Product name cannot be null")
        String name,

        @NotBlank(message = "description cannot be empty")
        @NotNull(message = "description cannot be null")
        String description,

        @NotNull(message = "price cannot be null")
        BigDecimal price,

        @NotNull(message = "category cannot be null")
        Category category,

        @Size(min = 1, message = "At least one image is required")
        List<Image> images) {
    public Product toProduct(){
        Product product = Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .category(category)
                .images(images)
                .build();

        if (images != null) {
            for (Image image : images) {
                image.setProduct(product);
            }
        }
        product.setImages(images);
        return product;
    }
}
