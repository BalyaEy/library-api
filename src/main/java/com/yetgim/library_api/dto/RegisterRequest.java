package com.yetgim.library_api.dto;

import com.yetgim.library_api.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private Role Role;

}
