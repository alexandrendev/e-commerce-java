package com.alexandre.eCommerce.Domain.user.dto;

import com.alexandre.eCommerce.Domain.user.UserAddress;

public record UserAddressDTO(String street,
                             String number,
                             Long city,
                             String complement) {

    public UserAddress toAddress(){
        return UserAddress.builder()
                .street(street)
                .number(number)
                .complement(complement)
                .city(city)
                .build();
    }
}
