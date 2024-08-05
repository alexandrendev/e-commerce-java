package com.alexandre.eCommerce.repositories.product;

import com.alexandre.eCommerce.Domain.product.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true, value = """
        SELECT quantity FROM inventory WHERE product_id = :productId;
        """)
    int checkProductInventory(@Param("productId")Long productId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = """
        INSERT INTO product(name, description, price, category) VALUES (:name, :description, :price, :category);
        """)
    int insertNewProduct(@Param("name") String name, @Param("description") String description, @Param("price") BigDecimal price, @Param("category") String category);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
        INSERT INTO inventory (product_id, warehouse_id, quantity) VALUES (:productId, :warehouseId :quantity);
""")
    int insertExistingProduct(@Param("warehouseId") Long wareHouseId,@Param("productId") Long productId,@Param("quantity") int quantity);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
        INSERT INTO image (url, product_id) VALUES (:url, :productId);
        """)
    int saveImage(@Param("url") String url, @Param("productId") Long productId);
}
