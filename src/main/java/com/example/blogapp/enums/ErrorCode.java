package com.example.blogapp.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    BLOG_NOT_FOUND("Blog not found with given id", "404", HttpStatus.NOT_FOUND.name()),
    STUDENT_NOT_FOUND("Student not found with given id", "404", HttpStatus.NOT_FOUND.name()),
    USER_NOT_FOUND("User not found with given id", "404", HttpStatus.NOT_FOUND.name()),
    NOT_FOUND("Not found", "404", HttpStatus.NOT_FOUND.name());

    private String message;
    private String code;
    private HttpStatus status;

    ErrorCode(String message, String code, String status) {
        this.message = message;
        this.code = code;
        this.status = HttpStatus.valueOf(status);
    }
}
