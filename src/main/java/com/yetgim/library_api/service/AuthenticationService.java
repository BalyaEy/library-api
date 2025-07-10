package com.yetgim.library_api.service;

import com.yetgim.library_api.dto.AuthRequest;
import com.yetgim.library_api.dto.AuthResponse;
import com.yetgim.library_api.dto.RegisterRequest;
import com.yetgim.library_api.entity.User;
import com.yetgim.library_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        String hashedPass = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(hashedPass)
                .role(request.getRole())
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(new HashMap<>(), user.getUsername());

        return AuthResponse.builder()
                .message("User registered successfully")
                .token(token)
                .build();
    }


    public AuthResponse authenticate(AuthRequest request) {

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String token = jwtService.generateToken(new HashMap<>(), user.getUsername());

        return AuthResponse.builder()
                .token(token)
                .message("Authentication successful")
                .build();
    }

}
