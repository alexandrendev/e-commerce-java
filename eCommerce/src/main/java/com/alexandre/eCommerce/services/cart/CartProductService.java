package com.alexandre.eCommerce.services.cart;

import com.alexandre.eCommerce.Domain.cart.CartProduct;
import com.alexandre.eCommerce.Domain.product.Product;
import com.alexandre.eCommerce.repositories.cart.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartProductService {
    private CartProductRepository repository;

    public List<Product> getProducts(Long cartId) {
        List<CartProduct> products = repository.findByCartId(cartId);
        List<Product> result = new ArrayList<>();
        products.forEach(product -> {
            result.add(product.getProduct());
        });
        return result;
    }

    public void cleanCart(Long cartId){
        repository.deleteByCartId(cartId);
    }

    @Autowired
    public CartProductService(CartProductRepository repository) {
        this.repository = repository;
    }
}
