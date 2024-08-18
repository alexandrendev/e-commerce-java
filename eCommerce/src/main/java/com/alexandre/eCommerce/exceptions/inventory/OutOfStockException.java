package com.alexandre.eCommerce.exceptions.inventory;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String message) {
        super(message);
    }
}
