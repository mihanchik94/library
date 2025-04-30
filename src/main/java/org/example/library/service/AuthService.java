package org.example.library.service;

import org.example.library.dto.AuthRequestDto;
import org.example.library.dto.AuthUserDto;
import org.example.library.dto.UserRegistrationDto;

import java.util.Map;

public interface AuthService {
    AuthUserDto register(UserRegistrationDto userRegistrationDto);
    Map<String, String> login(AuthRequestDto authRequest);
}
