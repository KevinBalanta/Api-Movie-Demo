package com.example.demo.dto;

import com.example.demo.entity.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    @NotEmpty(message = "firstname cannot be empty")
    private String firstname;
    @NotEmpty(message = "email cannot be empty")
    private String email;
    @NotEmpty(message = "password cannot be empty")
    private String password;
    @NotEmpty(message = "role cannot be empty")
    private Role role;
}
