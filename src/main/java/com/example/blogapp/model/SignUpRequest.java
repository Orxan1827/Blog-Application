package com.example.blogapp.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpRequest {

    @NotBlank
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "Numbers and the first letter starting with a lowercase letter not allowed")
    private String name;

    @Min(0)
    private int age;

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 7,max = 15)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "The password must be at least 8 characters , contain at least 1 digit, least 1 lowercase letter, least 1 uppercase letter and must not contain spaces.")
    private String password;

    @NotBlank
    @Email(regexp = "^(.+)@(.+)$", message = "Email is wrong")
    private String email;
}