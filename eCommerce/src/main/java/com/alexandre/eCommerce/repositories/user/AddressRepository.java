package com.alexandre.eCommerce.repositories.user;

import com.alexandre.eCommerce.Domain.user.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<UserAddress, Long> {
}
