package com.alexandre.eCommerce.Controller.cart;

import com.alexandre.eCommerce.Domain.cart.Cart;
import com.alexandre.eCommerce.Domain.product.Product;
import com.alexandre.eCommerce.infra.security.TokenService;
import com.alexandre.eCommerce.services.cart.CartService;
import com.alexandre.eCommerce.services.inventory.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {
    private final TokenService tokenService;
    private CartService service;
    private InventoryService inventoryService;


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
        if (!inventoryService.checkInventory(productId, 1L)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Out of stock.");
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


    @Operation(description = "Operation to list all products in cart.", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get products successfully."),
            @ApiResponse(responseCode = "400", description = "Operation cannot continue because the provided data is invalid."),
            @ApiResponse(responseCode = "401", description = "Not authenticated or credentials are invalid.")
    }
    )
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getCartProducts(@RequestHeader String tokenHeader){
        Long userId = tokenService.getIdFromToken(tokenHeader.substring(7));
        Cart cart = service.findCartByUserId(userId);

        List<Product> products = service.getProducts(cart.getId());

        return ResponseEntity.ok().body(products);
    }

    @Autowired
    public CartController(CartService service, TokenService tokenService, InventoryService inventoryService) {
        this.service = service;
        this.tokenService = tokenService;
        this.inventoryService = inventoryService;
    }
}
