package com.yetgim.library_api.service;

import com.yetgim.library_api.dto.AuthRequest;
import com.yetgim.library_api.dto.AuthResponse;
import com.yetgim.library_api.dto.RegisterRequest;
import com.yetgim.library_api.entity.Role;
import com.yetgim.library_api.entity.User;
import com.yetgim.library_api.exception.UserNotFoundException;
import com.yetgim.library_api.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(new HashMap<>(), user.getUsername(), jwtService.getJwtExpiration());
        String refreshToken = jwtService.generateRefreshToken(user);

        return new AuthResponse(token, "Register successful", token, refreshToken);
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + request.getEmail()));

        String token = jwtService.generateToken(new HashMap<>(), user.getUsername(), jwtService.getJwtExpiration());
        String refreshToken = jwtService.generateRefreshToken(user);

        return new AuthResponse(token, "Login successful", token, refreshToken);
    }

    public AuthResponse refreshToken(HttpServletRequest request, HttpServletResponse response) {
        final String authHeader = request.getHeader("Authorization");
        final String refreshToken;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new AuthResponse(null, "Missing refresh token", null, null);
        }

        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);

        if (username != null && jwtService.isTokenValid(refreshToken, username)) {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

            String newAccessToken = jwtService.generateToken(new HashMap<>(), user.getUsername(), jwtService.getJwtExpiration());
            return new AuthResponse(newAccessToken, "Token refreshed", newAccessToken, refreshToken);
        }

        return new AuthResponse(null, "Invalid refresh token", null, null);
    }
}
