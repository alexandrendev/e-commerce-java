package com.alexandre.eCommerce.services.inventory;

import com.alexandre.eCommerce.Domain.inventory.Inventory;
import com.alexandre.eCommerce.Domain.product.Product;
import com.alexandre.eCommerce.Domain.product.ProductEntryRequest;
import com.alexandre.eCommerce.exceptions.product.ProductNotFoundException;
import com.alexandre.eCommerce.repositories.inventory.InventoryRepository;
import com.alexandre.eCommerce.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private final ProductRepository productRepository;
    InventoryRepository inventoryRepository;

    public boolean save(ProductEntryRequest data) {
        Product product = productRepository.findById(data.id()).orElseThrow(
                ()-> new ProductNotFoundException("Product Not Found")
        );

        Inventory inventory = Inventory.builder()
                .product(product)
                .quantity(data.quantity())
                .build();

        inventoryRepository.save(inventory);
        return true;
    }


    public boolean decrementInventory(Long productId, int quantity){
        int rowsAffected = inventoryRepository.decrementInventoryQuantity(productId, quantity);
        return rowsAffected > 0;
    }

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }
}
