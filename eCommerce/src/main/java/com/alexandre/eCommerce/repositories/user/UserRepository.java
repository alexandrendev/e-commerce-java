package com.alexandre.eCommerce.repositories.user;

import com.alexandre.eCommerce.Domain.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
        INSERT INTO cart(customer_id) VALUES (:userId);
        """)
    int createCartByUserId(@Param("userId") Long id);

}
