package com.alexandre.eCommerce.repositories.order;

import com.alexandre.eCommerce.Domain.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
