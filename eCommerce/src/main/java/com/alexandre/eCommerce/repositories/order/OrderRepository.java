package com.alexandre.eCommerce.repositories.order;

import com.alexandre.eCommerce.Domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
