package com.alexandre.eCommerce.Service;

import com.alexandre.eCommerce.DTO.ProductDTO;
import com.alexandre.eCommerce.Domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProductDTO> findAll() {
        String sql = "SELECT * FROM product";
        List<Product> products = jdbcTemplate.queryForList(sql, Product.class);
        return products.stream()
                .map(p -> new ProductDTO(p.getName(), p.getDescription(), p.getPrice(), p.getCategory()))
                .collect(Collectors.toList());
    }
}
