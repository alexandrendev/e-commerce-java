package com.alexandre.eCommerce.Domain.user;

import jakarta.persistence.*;

@Entity
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;
}
