package com.alexandre.eCommerce.Controller;

import com.alexandre.eCommerce.Domain.DTO.ProductDTO;
import com.alexandre.eCommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
