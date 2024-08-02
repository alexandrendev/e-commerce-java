package com.alexandre.eCommerce.Controller;

import com.alexandre.eCommerce.DTO.ProductDTO;
import com.alexandre.eCommerce.Domain.Product;
import com.alexandre.eCommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        return ResponseEntity.ok(products);
    }
}
