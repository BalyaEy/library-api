package com.yetgim.library_api.controller;

import com.yetgim.library_api.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @GetMapping("/hello")
    public String hello() {
        return "👋 Hello, authenticated user!";
    }
    @GetMapping("/test-error")
    public ResponseEntity<String> testError() {
        throw new UserNotFoundException("gus_test");
    }

}
