package com.alexandre.eCommerce.repositories.cart;

import com.alexandre.eCommerce.Domain.cart.Cart;
import com.alexandre.eCommerce.Domain.product.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
        INSERT INTO product_cart(cart_id, product_id) VALUES (:cartId, :productId)""")
    int addProductToCart(@Param("cartId") Long cartId, @Param("productId") Long productId);

    @Query(nativeQuery = true, value = """
        SELECT id FROM cart WHERE customer_id = :userId;""")
    Long findCartIdByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
        WITH cte AS (
            SELECT id
            FROM product_cart
            WHERE cart_id = :cartId AND product_id = :productId
            LIMIT 1
        )
        DELETE FROM product_cart
        WHERE id IN (SELECT id FROM cte);""")
    int deleteProductFromCartByProductId(@Param("productId") Long productId, @Param("cartId") Long cartId);
}
