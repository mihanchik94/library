package org.example.library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.library.dto.AuthRequestDto;
import org.example.library.dto.AuthUserDto;
import org.example.library.dto.UserRegistrationDto;
import org.example.library.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Authentication Controller", description = "API for working with authentication")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register User")
    @PostMapping("/register")
    public ResponseEntity<AuthUserDto> addNewUser(@RequestBody UserRegistrationDto user) {
        return ResponseEntity.ok(authService.register(user));
    }

    @Operation(summary = "Authenticate user")
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequestDto authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

}
