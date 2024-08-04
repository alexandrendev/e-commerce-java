package com.alexandre.eCommerce.repositories;

import com.alexandre.eCommerce.Domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findAll();
}
