package com.yetgim.library_api.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException(String username) {
        super("User not found with username: " + username, HttpStatus.NOT_FOUND);
    }
}
