package com.lovedays.kings_cryptos.controllers;

import java.util.Map;
import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lovedays.kings_cryptos.Components.JwtUtil;
import com.lovedays.kings_cryptos.Dtos.ApplicationUserDto;
import com.lovedays.kings_cryptos.Dtos.LoginDto;
import com.lovedays.kings_cryptos.Model.ApplicationUser;
import com.lovedays.kings_cryptos.Services.ApplicationUserService;
import com.lovedays.kings_cryptos.UserDetails.UserDetailServiceIMPL;

@Controller
public class RegistrationController {
     
    private final UserDetailServiceIMPL userDetailServiceIMPL;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil  jwtUtil ;
    private final ApplicationUserService applicationUserService;

  
    public RegistrationController(UserDetailServiceIMPL userDetailServiceIMPL,
            AuthenticationManager authenticationManager, JwtUtil jwtUtil,
            ApplicationUserService applicationUserService) {
        this.userDetailServiceIMPL = userDetailServiceIMPL;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.applicationUserService = applicationUserService;
    }

    @PostMapping("/api/user/register")
    public ResponseEntity<?> registerUser(@RequestBody ApplicationUserDto applicationUserDto) {

    Optional<ApplicationUser> existing = applicationUserService.findUserByEmail(applicationUserDto.getEmail());

    if (existing.isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("message", "Email already registered"));
    }

    ApplicationUserDto saved = applicationUserService.RegisterUser(applicationUserDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
            "id", saved.getId(),
            "userName", saved.getUserName(),
            "email", saved.getEmail(),
            "message", "Account created successfully"
    ));
}

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            UserDetails user = userDetailServiceIMPL.loadUserByUsername(request.getEmail());

            String role = user.getAuthorities().iterator().next().getAuthority();
            String token = jwtUtil.generateToken(user.getUsername(), role);

            return ResponseEntity.ok(Map.of(
                "token", token,
                "role", role
            ));

        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password"));
        }
    }
}
