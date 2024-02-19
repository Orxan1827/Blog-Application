package com.example.blogapp.controller;

import com.example.blogapp.model.StudentUpdateRequest;
import com.example.blogapp.model.dto.StudentDto;
import com.example.blogapp.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping("/{student-id}")
    public void updateStudent(@Valid @PathVariable("student-id") Long studentId, @RequestBody StudentUpdateRequest updateRequest) {
        studentService.updateStudent(studentId,updateRequest);
    }

}
