package com.alexandre.eCommerce.Controller.cart;

import com.alexandre.eCommerce.infra.security.TokenService;
import com.alexandre.eCommerce.services.cart.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
public class CartController {
    private final TokenService tokenService;
    CartService service;


    @Operation(description = "Operation to add a product to cart.", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product added to cart."),
            @ApiResponse(responseCode = "400", description = "Operation cannot continue because the provided data is invalid."),
            @ApiResponse(responseCode = "401", description = "The provided credentials are not valid.")
    }
    )
    @PostMapping("/add")
    public ResponseEntity addProductToCart(@RequestHeader String tokenHeader, @RequestBody Long productId) {
        String token = tokenHeader.substring(7);
        Long userId = tokenService.getIdFromToken(token);

        if(service.addProductToCart(userId, productId)) return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }

    @Operation(description = "Operation to remove a product from cart.", method = "DELETE")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product removed from cart."),
            @ApiResponse(responseCode = "400", description = "Operation cannot continue because the provided data is invalid."),
            @ApiResponse(responseCode = "401", description = "The provided credentials are not valid.")
    }
    )
    @DeleteMapping()
    public ResponseEntity removeProductFromCart(@RequestHeader String tokenHeader, @RequestBody Long productId) {
        String token = tokenHeader.substring(7);
        Long userId = tokenService.getIdFromToken(token);

        if(service.deleteFromCartByProductId(userId, productId)) return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }

    @Autowired
    public CartController(CartService service, TokenService tokenService) {
        this.service = service;
        this.tokenService = tokenService;
    }
}
