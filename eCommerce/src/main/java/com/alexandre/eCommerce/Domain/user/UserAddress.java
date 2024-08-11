package com.alexandre.eCommerce.Domain.user;

import com.alexandre.eCommerce.Domain.user.dto.UserAddressDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String street;
    private String number;
    private String complement;
    @Transient
    private Long city;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;


    public UserAddressDTO toDTO(){
        return new UserAddressDTO(street, number, city, complement);
    }
}
