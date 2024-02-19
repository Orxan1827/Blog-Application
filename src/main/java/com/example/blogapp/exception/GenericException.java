package com.example.blogapp.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenericException extends RuntimeException {

    private HttpStatus httpStatus;
    private String errorCode;
    private String errorMessage;
}

