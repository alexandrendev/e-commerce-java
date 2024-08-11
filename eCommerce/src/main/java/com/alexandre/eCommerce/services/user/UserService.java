package com.alexandre.eCommerce.services.user;

import com.alexandre.eCommerce.Domain.user.User;
import com.alexandre.eCommerce.Domain.user.UserAddress;
import com.alexandre.eCommerce.Domain.user.dto.UserAddressDTO;
import com.alexandre.eCommerce.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository repository;
    AddressRepository addressRepository;


    public UserAddressDTO addAddress(UserAddressDTO address, Long userId) {
        Optional<User> user = repository.findById(userId);
        if (user.isPresent()) {
            UserAddress userAddress = address.toAddress();
            userAddress.setUser(user.get());
            UserAddress savedAddress = addressRepository.save(userAddress);
            return savedAddress.toDTO();
        }
        return null;
    }

    public List<UserAddressDTO> getUserAddresses(Long userId) {

        List<UserAddress> addresses =
                repository.findById(userId)
                    .map(User::getAddresses)
                    .orElseThrow(() -> new RuntimeException("User not found"));

        List<UserAddressDTO> dtos = new ArrayList<>();
        for (UserAddress userAddress : addresses) {
            dtos.add(userAddress.toDTO());
        }
        return dtos;
    }

    @Autowired
    public UserService(UserRepository repository, AddressRepository addressRepository) {
        this.repository = repository;
        this.addressRepository = addressRepository;
    }
}
