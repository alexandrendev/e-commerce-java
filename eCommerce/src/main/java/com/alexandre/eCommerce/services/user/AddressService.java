package com.alexandre.eCommerce.services.user;

import com.alexandre.eCommerce.Domain.user.UserAddress;
import com.alexandre.eCommerce.repositories.user.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public UserAddress getAddressById(Long id){
        Optional<UserAddress> address = repository.findById(id);
        if(address.isPresent()) return address.get();
        return null;
    }
}
