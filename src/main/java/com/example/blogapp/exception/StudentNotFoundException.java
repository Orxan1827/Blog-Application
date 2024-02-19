package com.example.blogapp.exception;

import static com.example.blogapp.enums.ErrorCode.STUDENT_NOT_FOUND;

public class StudentNotFoundException extends GenericException {

    public StudentNotFoundException() {
        super(STUDENT_NOT_FOUND.getStatus(), STUDENT_NOT_FOUND.getCode(), STUDENT_NOT_FOUND.getMessage());
    }
}
