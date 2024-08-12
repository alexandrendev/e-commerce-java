package com.alexandre.eCommerce.repositories.product;

import com.alexandre.eCommerce.Domain.product.Category;
import com.alexandre.eCommerce.Domain.product.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Query(nativeQuery = true, value = """
//        SELECT quantity FROM inventory WHERE product_id = :productId;
//        """)
//
//        SELECT w.name, i.product_id, i.quantity
//FROM Inventory i
//JOIN Warehouses w ON i.warehouse_id = w.warehouse_id
//WHERE i.product_id = :product_id;
//
//    int checkProductInventory(@Param("productId")Long productId);
//
//    @Modifying
//    @Transactional
//    @Query(nativeQuery = true, value = """
//        INSERT INTO inventory (product_id, warehouse_id, quantity) VALUES (:productId, :warehouseId :quantity);
//""")
//    int insertExistingProduct(@Param("warehouseId") Long wareHouseId,@Param("productId") Long productId,@Param("quantity") int quantity);

    Page<Product> findByCategory(Category category, Pageable pageable);
}
