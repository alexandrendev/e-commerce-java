package com.alexandre.eCommerce.Domain.product;

public enum Category {
    HARDWARE(1, "Hardware"),
    PERIPHERAL(2, "Peripheral"),
    OTHER(3, "Other");

    private int number;
    private String name;

    Category(int number, String name) {
        this.number = number;
        this.name = name;
    }
}
