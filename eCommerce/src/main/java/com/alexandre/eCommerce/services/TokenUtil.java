package com.alexandre.eCommerce.services;

import com.alexandre.eCommerce.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenUtil {
    private final TokenService tokenService;

    public Long extractIdFromToken(String token){
        return tokenService.getIdFromToken(token.substring(7));
    }

    @Autowired
    public TokenUtil(TokenService tokenService) {
        this.tokenService = tokenService;
    }
}
