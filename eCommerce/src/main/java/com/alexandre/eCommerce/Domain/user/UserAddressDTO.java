package com.alexandre.eCommerce.Domain.user;

public record UserAddressDTO(String street,
                             String number,
                             String city,
                             String complement) {
}
