package com.alexandre.eCommerce.Domain.DTO;

import com.alexandre.eCommerce.Domain.user.UserRole;

public record RegisterDTO(String name, String username, String password, UserRole role) {
}
