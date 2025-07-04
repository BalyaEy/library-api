package com.yetgim.library_api.controller;

import com.yetgim.library_api.dto.AuthResponse;
import com.yetgim.library_api.dto.RegisterRequest;
import com.yetgim.library_api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

        public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        AuthResponse response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }
}
