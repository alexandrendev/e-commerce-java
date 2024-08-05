package com.alexandre.eCommerce.Domain.product;

import java.math.BigDecimal;
import java.util.List;

public record ProductDTO(String name, String description, BigDecimal price, Category category, List<Image> images) {
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
