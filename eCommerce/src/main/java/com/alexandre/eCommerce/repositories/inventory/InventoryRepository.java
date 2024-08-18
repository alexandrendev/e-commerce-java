package com.alexandre.eCommerce.repositories.inventory;

import com.alexandre.eCommerce.Domain.inventory.Inventory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
        UPDATE inventory SET quantity = quantity - :quantity WHERE product_id = :productId
    """)
    int decrementInventoryQuantity(@Param("productId") Long productId, @Param("quantity") int quantity);

    @Query(nativeQuery = true, value = """ 
        SELECT SUM(quantity) AS total FROM inventory WHERE product_id = :productId
        """)
    int findQuantityByProductId(@Param("productId") Long productId);
}
