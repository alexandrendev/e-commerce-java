package com.alexandre.eCommerce.repositories.inventory;

import com.alexandre.eCommerce.Domain.inventory.Inventory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
        UPDATE inventory i SET i.quantity = i.quantity - :quantity WHERE i.product_id = :product_id
    """)
    int decrementInventoryQuantity(Long productId, int quantity);
}
