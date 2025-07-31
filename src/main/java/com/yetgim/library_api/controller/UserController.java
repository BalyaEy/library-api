package com.yetgim.library_api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('ROLE_USER', ' ROLE_ADMIN')")
    public String profile() {
        return "🙋 Accessible to USER and ADMIN";
    }
}
