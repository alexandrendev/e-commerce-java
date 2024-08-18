package com.alexandre.eCommerce.Controller.product;

import com.alexandre.eCommerce.Domain.product.Product;
import com.alexandre.eCommerce.Domain.product.ProductDTO;
import com.alexandre.eCommerce.Domain.product.ProductEntryRequest;
import com.alexandre.eCommerce.services.inventory.InventoryService;
import com.alexandre.eCommerce.services.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/products")
public class ProductAdminController {
    private ProductService service;
    private InventoryService inventoryService;


    @Operation(description = "Operation to create a product.", method = "POST")
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


    @Operation(description = "Operation to register a product entry in the inventory.", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entry registered"),
            @ApiResponse(responseCode = "400", description = "Operation cannot continue because the provided data is invalid."),
            @ApiResponse(responseCode = "401", description = "The provided credentials are not valid or the user is not authorized to perform this action.")
    }
    )
    @PostMapping("/entry")
    public ResponseEntity registerProductEntry(@RequestBody ProductEntryRequest data) {
        boolean result = inventoryService.save(data);
        return result ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }


    @Autowired
    public ProductAdminController(ProductService service, InventoryService inventoryService) {
        this.service = service;
        this.inventoryService = inventoryService;
    }
}
