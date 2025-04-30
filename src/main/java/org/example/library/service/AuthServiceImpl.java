package org.example.library.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.dto.AuthRequestDto;
import org.example.library.dto.AuthUserDto;
import org.example.library.dto.UserRegistrationDto;
import org.example.library.exception.BadRequestException;
import org.example.library.mapper.UserMapper;
import org.example.library.model.User;
import org.example.library.repository.UserRepository;
import org.example.library.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public AuthUserDto register(UserRegistrationDto userRegistrationDto) {
        User user = userMapper.fromUserRegistrationDtoToUserWithRoleUser(userRegistrationDto);
        user.setPassword(encoder.encode(user.getPassword()));
        return userMapper.fromUserToAuthUserDto(userRepository.save(user));
    }

    @Override
    public Map<String, String> login(AuthRequestDto authRequest) {
        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with email: %s not found", authRequest.getEmail())));
        if (encoder.matches(authRequest.getPassword(), user.getPassword())) {
            Map<String, String> result = new HashMap<>();
            result.put("email", user.getEmail());
            result.put("token", jwtTokenProvider.createToken(user));
            return result;
        } else {
            log.info("IN findByEmailAndPassword - Invalid username or password");
            throw new BadRequestException("Invalid username or password");
        }
    }

}
