package com.alexandre.eCommerce.Domain.order.dtos;

import com.alexandre.eCommerce.Domain.order.PaymentMethod;

public record CreateOrderRequest(PaymentMethod method, Long addressId) {
}
