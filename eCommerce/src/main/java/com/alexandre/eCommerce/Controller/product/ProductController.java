package com.alexandre.eCommerce.Controller.product;

import com.alexandre.eCommerce.Domain.product.CategoryRequest;
import com.alexandre.eCommerce.Domain.product.Product;
import com.alexandre.eCommerce.Domain.product.ProductDTO;
import com.alexandre.eCommerce.services.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService service;


    @Operation(description = "Operation to list all products paginated.", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listed Products with success."),
            @ApiResponse(responseCode = "204", description = "No content."),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    }
    )
    @GetMapping()
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
        Page<Product> products = service.findAll(pageable);
        if (!products.hasContent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(products);
    }

    @Operation(description = "Operation to get paginated products by category.", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    }
    )


    @GetMapping("category/")
    public ResponseEntity<Page<ProductDTO>> getProductsByCategory(Pageable pageable, @RequestBody CategoryRequest category) {
        Page<ProductDTO> products = service.getProductsbyCategory(category.getCategory(), pageable);
        if (products.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(products);
    }




    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }
}
