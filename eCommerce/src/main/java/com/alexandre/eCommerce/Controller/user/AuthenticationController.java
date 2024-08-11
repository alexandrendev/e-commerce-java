package com.alexandre.eCommerce.Controller.user;

import com.alexandre.eCommerce.Domain.user.dto.AuthenticationDTO;
import com.alexandre.eCommerce.Domain.user.dto.LoginResponseDTO;
import com.alexandre.eCommerce.Domain.user.dto.RegisterDTO;
import com.alexandre.eCommerce.Domain.user.User;
import com.alexandre.eCommerce.infra.security.TokenService;
import com.alexandre.eCommerce.repositories.user.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final TokenService tokenService;
    AuthenticationManager authenticationManager;
    UserRepository repository;

    @Operation(description = "Operation to login with an existing account", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfuly logged in."),
            @ApiResponse(responseCode = "400", description = "Operation cannot continue because the provided data is invalid."),
            @ApiResponse(responseCode = "401", description = "The provided credentials are not valid.")
    }
    )
    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(description = "Operation to remove a product from cart.", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account registered."),
            @ApiResponse(responseCode = "400", description = "Operation cannot continue because the provided data is invalid."),
            @ApiResponse(responseCode = "401", description = "User don't have role to register an account.")
    }
    )
    @PostMapping("/register")
    public ResponseEntity register (@RequestBody @Valid RegisterDTO data){
        if(repository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.username(), encryptedPassword, data.role());
        User user = repository.save(newUser);
        repository.createCartByUserId(user.getId());
        return ResponseEntity.ok().build();
    }

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository repository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }
}
