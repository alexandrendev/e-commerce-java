package com.alexandre.eCommerce.services.user;

import com.alexandre.eCommerce.Domain.user.UserAddressDTO;
import com.alexandre.eCommerce.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository repository;

    public boolean addAddress(UserAddressDTO address, Long userId) {
        int rowsAffected = repository.addUserAddress(address.street(),
                address.number(),
                address.complement(),
                address.city(),
                userId);

        return rowsAffected > 0;
    }

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
}
