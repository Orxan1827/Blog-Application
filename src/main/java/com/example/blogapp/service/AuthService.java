package com.example.blogapp.service;

import com.example.blogapp.entity.Student;
import com.example.blogapp.enums.ErrorCode;
import com.example.blogapp.exception.GenericException;
import com.example.blogapp.model.ChangePasswordRequest;
import com.example.blogapp.model.LoginRequest;
import com.example.blogapp.model.PasswordRequest;
import com.example.blogapp.model.SignUpRequest;
import com.example.blogapp.model.dto.StudentSignUpDto;
import com.example.blogapp.model.dto.TokenResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentService studentService;
    private final EmailService emailService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;

    public TokenResponseDto login(LoginRequest loginRequest) {
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            return TokenResponseDto
                    .builder()
                    .accessToken(tokenService.generateToken(auth))
                    .student(studentService.findStudent(loginRequest.getUsername()))
                    .build();
        } catch (final BadCredentialsException badCredentialsException) {
            throw GenericException.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .errorCode(ErrorCode.USER_NOT_FOUND.getCode())
                    .errorMessage("Invalid Username or Password").build();
        }
    }

    @Transactional
    public StudentSignUpDto signup(SignUpRequest signUpRequest) throws GenericException {
        var isAllReadyRegistered = studentService.existsByUsername(signUpRequest.getUsername());

        if (isAllReadyRegistered) {
            throw GenericException.builder().httpStatus(HttpStatus.FOUND)
                    .errorCode("409")
                    .errorMessage("Username: " + signUpRequest.getUsername() + "is already used").build();
        }

        var student = Student.builder()
                .name(signUpRequest.getName())
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .age(signUpRequest.getAge())
                .createdAt(LocalDateTime.now())
                .build();

        Student fromDb = studentService.create(student);

        return StudentSignUpDto.builder()
                .id(fromDb.getId())
                .username(fromDb.getUsername())
                .createdAt(fromDb.getCreatedAt())
                .build();
    }

    public void resetPassword(PasswordRequest request){
        var studentByEmail = studentService.findStudentByEmail(request.getEmail());
        String newPassword = generateNewPassword();

        String encodedPassword = encoder.encode(newPassword);

        studentByEmail.setPassword(encodedPassword);

        studentService.create(studentByEmail);

        emailService.sendPasswordResetEmail(studentByEmail.getEmail(),"Password message",newPassword);
    }

    public void changePassword(ChangePasswordRequest request){

        var studentByPassword = studentService.findStudentByEmail(request.getEmail());

        studentByPassword.setPassword(encoder.encode(request.getNewPassword()));

        studentService.create(studentByPassword);
    }

    private String generateNewPassword() {
        int length = 10;

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder newPassword = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            newPassword.append(characters.charAt(random.nextInt(characters.length())));
        }
        return newPassword.toString();
    }
}
