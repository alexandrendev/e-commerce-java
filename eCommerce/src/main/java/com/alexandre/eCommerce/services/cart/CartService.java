package com.alexandre.eCommerce.services.cart;

import com.alexandre.eCommerce.Domain.cart.Cart;
import com.alexandre.eCommerce.Domain.product.Product;
import com.alexandre.eCommerce.exceptions.cart.CartNotFoundException;
import com.alexandre.eCommerce.repositories.cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private CartRepository repository;
    private CartProductService cartProductService;

    public boolean addProductToCart(Long userId, Long productId) {
        Long cartId = repository.findCartIdByUserId(userId);
        return repository.addProductToCart(cartId, productId) == 1;
    }


    public boolean deleteFromCartByProductId(Long userId, Long productId){
        Long cartId = repository.findCartIdByUserId(userId);
        return repository.deleteProductFromCartByProductId(productId, cartId) == 1;
    }

    public List<Product> getProducts(Long cartId){
        return cartProductService.getProducts(cartId);
    }

    public Cart findCartByUserId(Long userId){
        Long cartId = repository.findCartIdByUserId(userId);
        if(cartId == null) throw new CartNotFoundException("Cart not found");

        Optional<Cart> cart = repository.findById(cartId);
        return cart.orElseThrow(() -> new CartNotFoundException("Cart not found"));
    }
    @Autowired
    public CartService(CartRepository cartRepository, CartProductService cartProductService) {
        this.repository = cartRepository;
        this.cartProductService = cartProductService;
    }
}
