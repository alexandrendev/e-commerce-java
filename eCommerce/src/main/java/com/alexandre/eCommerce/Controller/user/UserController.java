package com.alexandre.eCommerce.Controller.user;

import com.alexandre.eCommerce.Domain.user.UserAddressDTO;

import com.alexandre.eCommerce.infra.security.TokenService;
import com.alexandre.eCommerce.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("account")
public class UserController {
    UserService service;
    TokenService tokenService;


    @Operation(description = "Operation to add the user's address.", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "address registered successfully"),
            @ApiResponse(responseCode = "400", description = "Operation cannot continue because the provided data is invalid."),
            @ApiResponse(responseCode = "401", description = "The provided credentials are not valid/must be logged in to access this resource.")
    }
    )
    @PostMapping("/address")
    public ResponseEntity addAddress(@RequestHeader String tokenHeader, @RequestBody UserAddressDTO address) {
        String token = tokenHeader.substring(7);
        Long userId = tokenService.getIdFromToken(token);
        if(userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return service.addAddress(address, userId)
            ? ResponseEntity.created(null).build()
            : ResponseEntity.badRequest().build();
    }

    @Autowired
    public UserController(UserService service, TokenService tokenService) {
        this.service = service;
        this.tokenService = tokenService;
    }
}
