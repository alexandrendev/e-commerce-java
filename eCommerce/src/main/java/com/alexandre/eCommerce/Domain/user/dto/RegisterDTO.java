package com.alexandre.eCommerce.Domain.user.dto;

import com.alexandre.eCommerce.Domain.user.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotNull(message = "cannot be null")
        @NotBlank(message = "cannot be empty")
        String name,

        @NotNull(message = "cannot be null")
        @NotBlank(message = "cannot be empty")
        String username,

        @NotNull(message = "cannot be null")
        @NotBlank(message = "cannot be empty")
        String password,

        @NotNull(message = "cannot be null")
        UserRole role) {
}
