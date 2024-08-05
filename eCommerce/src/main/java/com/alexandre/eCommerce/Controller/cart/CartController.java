package com.alexandre.eCommerce.Controller.cart;

import com.alexandre.eCommerce.infra.security.TokenService;
import com.alexandre.eCommerce.services.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
public class CartController {
    private final TokenService tokenService;
    CartService service;


    @PostMapping("/add")
    public ResponseEntity addProductToCart(@RequestHeader String tokenHeader, @RequestBody Long productId) {
        String token = tokenHeader.substring(7);
        Long userId = tokenService.getIdFromToken(token);

        if(service.addProductToCart(userId, productId)) return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }

    @Autowired
    public CartController(CartService service, TokenService tokenService) {
        this.service = service;
        this.tokenService = tokenService;
    }
}
