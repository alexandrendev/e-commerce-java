package com.alexandre.eCommerce.Controller.product;

import com.alexandre.eCommerce.Domain.product.ProductDTO;
import com.alexandre.eCommerce.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = service.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(products);
    }

    @PostMapping()
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product,@RequestHeader("X-Image-URL") String imageURL) {
        ProductDTO dto = service.createProduct(product, imageURL);
        if (dto == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.created(null).body(dto);
    }
}
