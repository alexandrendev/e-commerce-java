package com.alexandre.eCommerce.service;

import com.alexandre.eCommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    public List<ProductDTO> findAll() {
//        return this.productRepository.findAll();
//    }
}
