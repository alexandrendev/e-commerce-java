package com.alexandre.eCommerce.Controller.user;

import com.alexandre.eCommerce.Domain.user.UserAddressDTO;

import com.alexandre.eCommerce.infra.security.TokenService;
import com.alexandre.eCommerce.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("account")
public class UserController {
    UserService service;
    TokenService tokenService;

    @PostMapping("/address")
    public ResponseEntity addAddress(@RequestHeader String tokenHeader, @RequestBody UserAddressDTO address) {
        String token = tokenHeader.substring(7);
        Long userId = tokenService.getIdFromToken(token);
        if (service.addAddress(address, userId)){
            return ResponseEntity.created(null).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Autowired
    public UserController(UserService service, TokenService tokenService) {
        this.service = service;
        this.tokenService = tokenService;
    }
}
