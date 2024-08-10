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

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
            INSERT INTO address (street, number, complement, city_id, customer_id) VALUES(:street,
                                                                                          :number,
                                                                                          :complement,
                                                                                          (SELECT id FROM city WHERE name = :cityName),
                                                                                          :userId)
            """)
    int addUserAddress(@Param("street") String street,
                       @Param("number") String number,
                       @Param("complement") String complement,
                       @Param("cityName") String cityName,
                       @Param("userId") Long userId);

}
