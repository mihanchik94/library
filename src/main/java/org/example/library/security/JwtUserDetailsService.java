package org.example.library.security;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.model.User;
import org.example.library.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with email: %s not found", username)));
        log.info("IN loadUserByUsername -  loaded username: {} successfully loaded", username);
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername -  loaded username: {} successfully loaded", username);
        return jwtUser;
    }
}
