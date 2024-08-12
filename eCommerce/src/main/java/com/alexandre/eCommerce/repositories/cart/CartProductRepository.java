package com.alexandre.eCommerce.repositories.cart;

import com.alexandre.eCommerce.Domain.cart.CartProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    List<CartProduct> findByCartId(Long cartId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
        DELETE FROM product_cart pc WHERE PC.cart_id = :cartId
        """)
    void deleteByCartId(@Param("cartId") Long cartId);
}
