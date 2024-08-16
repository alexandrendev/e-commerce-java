package com.alexandre.eCommerce.Domain.order;

import com.alexandre.eCommerce.Domain.user.User;
import com.alexandre.eCommerce.Domain.user.UserAddress;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;


    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "delivery_address_id")
    private UserAddress address;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
}
