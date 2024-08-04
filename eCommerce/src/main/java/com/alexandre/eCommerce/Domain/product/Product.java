package com.alexandre.eCommerce.Domain.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    public ProductDTO toDTO(){
        return new ProductDTO(this.getName(), this.getDescription(), this.getPrice(), this.getCategory());
    }
}
