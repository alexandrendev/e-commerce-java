package com.alexandre.eCommerce.repositories.user;

import com.alexandre.eCommerce.Domain.user.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<UserAddress, Long> {
}
