package com.alexandre.eCommerce.services;

import com.alexandre.eCommerce.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private CartRepository repository;

    public boolean addProductToCart(Long userId, Long productId) {
        Long cartId = repository.retrieveCartIdByUserId(userId);
        return repository.addProductToCart(cartId, productId) == 1;
    }

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.repository = cartRepository;
    }
}
