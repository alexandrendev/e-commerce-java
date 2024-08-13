package com.alexandre.eCommerce.services.product;

import com.alexandre.eCommerce.Domain.product.Category;
import com.alexandre.eCommerce.Domain.product.Product;
import com.alexandre.eCommerce.Domain.product.ProductDTO;
import com.alexandre.eCommerce.repositories.product.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO) {
        return productRepository.save(productDTO.toProduct()).toDTO();
    }

    public ProductDTO updateProduct(Product product) {
        return productRepository.save(product).toDTO();
    }

    public Page<ProductDTO> getProductsbyCategory(String category, Pageable pageable) {
        Page<Product> products = productRepository.findByCategory(Category.valueOf(category), pageable);
        return products.map(product -> new ProductDTO(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getImages()
        ));
    }
}
