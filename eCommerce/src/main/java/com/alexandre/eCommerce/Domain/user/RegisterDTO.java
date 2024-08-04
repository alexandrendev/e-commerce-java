package com.alexandre.eCommerce.Domain.user;

public record RegisterDTO(String name, String username, String password, UserRole role) {
}
