package com.alexandre.eCommerce.Controller.user;

import com.alexandre.eCommerce.Domain.user.dto.UserAddressDTO;

import com.alexandre.eCommerce.infra.security.TokenService;
import com.alexandre.eCommerce.services.TokenUtil;
import com.alexandre.eCommerce.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("account")
public class AccountController {
    UserService service;
    TokenUtil tokenUtil;


    @Operation(description = "Operation to add the user's address.", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "address registered successfully"),
            @ApiResponse(responseCode = "400", description = "Operation cannot continue because the provided data is invalid."),
            @ApiResponse(responseCode = "401", description = "The provided credentials are not valid/must be logged in to access this resource.")
    }
    )
    @PostMapping("/address")
    public ResponseEntity<UserAddressDTO> addAddress(@RequestHeader String tokenHeader, @RequestBody UserAddressDTO address) {
        Long userId = tokenUtil.extractIdFromToken(tokenHeader);
        if(userId == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        UserAddressDTO dto = service.addAddress(address, userId);
        if(dto != null) return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Operation(description = "Operation to get all addresses linked to an account ", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully."),
            @ApiResponse(responseCode = "400", description = "Operation cannot continue because the provided data is invalid."),
            @ApiResponse(responseCode = "204", description = "This account have no addresses linked itself")
    })
    @GetMapping("/address")
    public ResponseEntity<List<UserAddressDTO>> getUserAddresses (@RequestHeader String tokenHeader){
        Long userId = tokenUtil.extractIdFromToken(tokenHeader);
        if(userId == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        List<UserAddressDTO> addresses = service.getUserAddresses(userId);
        if(addresses.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    @Autowired
    public AccountController(UserService service, TokenUtil tokenUtil) {
        this.service = service;
        this.tokenUtil = tokenUtil;
    }
}
