package com.yetgim.library_api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/secret")
    @PreAuthorize("hasRole('ADMIN')")
    public String secret() {
        return "🛡️ Only admins can see this!";
    }
}

