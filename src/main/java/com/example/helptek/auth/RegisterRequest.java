package com.example.helptek.auth;

import com.example.helptek.role.Role;
import com.example.helptek.validator.ValidPassword;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String mobile;
    private String email;
    private String password;
    private String role;
}
