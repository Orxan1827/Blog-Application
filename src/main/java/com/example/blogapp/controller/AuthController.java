package com.example.blogapp.controller;

import com.example.blogapp.model.ChangePasswordRequest;
import com.example.blogapp.model.LoginRequest;
import com.example.blogapp.model.PasswordRequest;
import com.example.blogapp.model.SignUpRequest;
import com.example.blogapp.model.dto.StudentSignUpDto;
import com.example.blogapp.model.dto.TokenResponseDto;
import com.example.blogapp.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<StudentSignUpDto> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authService.signup(signUpRequest));
    }

    @PostMapping("/reset")
    public void resetPassword(@Valid @RequestBody PasswordRequest request){
        authService.resetPassword(request);
    }

    @PutMapping("/change")
    public void changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        authService.changePassword(request);
    }
}

