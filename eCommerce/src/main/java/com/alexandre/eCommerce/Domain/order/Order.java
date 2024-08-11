package com.alexandre.eCommerce.Domain.order;

import com.alexandre.eCommerce.Domain.user.User;
import com.alexandre.eCommerce.Domain.user.UserAddress;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OrderStatus status;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;


    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "delivery_address_id")
    private UserAddress address;
}
