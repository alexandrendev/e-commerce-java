package com.alexandre.eCommerce.Domain;

import com.alexandre.eCommerce.Domain.DTO.ProductDTO;
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
    @Column(name = "category")
    private Category category;

    ProductDTO toDTO(){
        return new ProductDTO(this.getName(), this.getDescription(), this.getPrice(), this.getCategory());
    }
}
