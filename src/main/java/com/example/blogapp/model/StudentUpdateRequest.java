package com.example.blogapp.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpdateRequest {

    @NotBlank
    private String username;

    @NotBlank
    @Email(regexp = "^(.+)@(.+)$", message = "Email is wrong")
    private String email;
}
