package com.alexandre.eCommerce.services.product;

import com.alexandre.eCommerce.Domain.product.Product;
import com.alexandre.eCommerce.Domain.product.ProductDTO;
import com.alexandre.eCommerce.repositories.product.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO, String imageURL) {
        Product savedProduct = productRepository.save(productDTO.toProduct());

        if (productRepository.saveImage(imageURL, savedProduct.getId()) == 1) {
            return savedProduct.toDTO();
        }
        return null;
    }
    public boolean checkInventory(Long productId, int quantity) {
        return true;
    }
}
