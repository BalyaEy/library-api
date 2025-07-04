package com.yetgim.library_api.service;


import com.yetgim.library_api.dto.AuthResponse;
import com.yetgim.library_api.dto.RegisterRequest;
import com.yetgim.library_api.entity.User;
import com.yetgim.library_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request){

        String hashedPass = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .build();


        userRepository.save(user);

        return AuthResponse.builder()
                .message("User registered successfully")
                .build();
    }
}
