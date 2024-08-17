package com.alexandre.eCommerce.Domain.inventory;

import com.alexandre.eCommerce.Domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
