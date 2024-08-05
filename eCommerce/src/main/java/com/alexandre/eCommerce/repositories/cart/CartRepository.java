package com.alexandre.eCommerce.repositories.cart;

import com.alexandre.eCommerce.Domain.cart.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
        INSERT INTO product_cart(cart_id, product_id) VALUES (:cartId, :productId)
        """)
    int addProductToCart(@Param("cartId") Long cartId, @Param("productId") Long productId);

    @Query(nativeQuery = true, value = """
        SELECT id FROM cart WHERE customer_id = :userId;
        """)
    Long retrieveCartIdByUserId(@Param("userId") Long userId);
}
