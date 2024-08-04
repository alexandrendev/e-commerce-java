package com.alexandre.eCommerce.services;

import com.alexandre.eCommerce.Domain.product.Product;
import com.alexandre.eCommerce.Domain.product.ProductDTO;
import com.alexandre.eCommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDTO(
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getCategory()
                ))
                .toList();
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productDTO.toProduct();
        return productRepository.save(product).toDTO();
    }
    public boolean checkInventory(Long productId, int quantity) {
        return true;
    }
}
