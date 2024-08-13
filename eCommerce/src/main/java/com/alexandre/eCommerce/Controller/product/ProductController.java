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


    @Operation(description = "Operation to list all products.", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listed Products with success."),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    }
    )
    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = service.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(products);
    }

    @Operation(description = "Operation to remove a product from cart.", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product created"),
            @ApiResponse(responseCode = "400", description = "Operation cannot continue because the provided data is invalid."),
            @ApiResponse(responseCode = "401", description = "The provided credentials are not valid or the user is not authorized to perform this action.")
    }
    )
    @PostMapping()
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product) {
        ProductDTO dto = service.createProduct(product);
        if (dto == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.created(null).body(dto);
    }

    @Operation(description = "Operation to update an existing product.", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product updated"),
            @ApiResponse(responseCode = "400", description = "Operation cannot continue because the provided data is invalid."),
            @ApiResponse(responseCode = "401", description = "The provided credentials are not valid or the user is not authorized to perform this action.")
    }
    )
    @PutMapping()
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody Product product) {
        ProductDTO dto = service.updateProduct(product);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("category/")
    public ResponseEntity<Page<ProductDTO>> getProductsByCategory(Pageable pageable, @RequestBody CategoryRequest category) {
        Page<ProductDTO> products = service.getProductsbyCategory(category.getCategory(), pageable);
        if (products.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(products);
    }




    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }
}
