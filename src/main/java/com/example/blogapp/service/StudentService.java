package com.example.blogapp.service;

import com.example.blogapp.entity.Student;
import com.example.blogapp.exception.GenericException;
import com.example.blogapp.exception.StudentNotFoundException;
import com.example.blogapp.mapper.StudentMapper;
import com.example.blogapp.model.StudentUpdateRequest;
import com.example.blogapp.model.dto.StudentDto;
import com.example.blogapp.model.dto.StudentSignUpDto;
import com.example.blogapp.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    @Transactional
    protected Student create(Student student) {
        return studentRepository.save(student);
    }

    protected Student findStudentByUsername(String username) {
        return studentRepository.findByUsername(username)
                .orElseThrow(notFoundStudent(HttpStatus.NOT_FOUND));
    }

    protected Student findStudentByEmail(String email) {
        return studentRepository
                .findStudentByEmail(email)
                .orElseThrow(StudentNotFoundException::new);
    }

    protected Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(StudentNotFoundException::new);
    }

    public StudentSignUpDto findStudent(String username) {
        var user = findStudentByUsername(username);
        return StudentSignUpDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public StudentSignUpDto findUserInContext() {
        final Authentication authentication = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).orElseThrow(notFoundStudent(HttpStatus.UNAUTHORIZED));
        final UserDetails details = Optional.ofNullable((UserDetails) authentication.getPrincipal()).orElseThrow(notFoundStudent(HttpStatus.UNAUTHORIZED));
        return findStudent(details.getUsername());
    }

    private static Supplier<GenericException> notFoundStudent(HttpStatus unauthorized) {
        return () -> GenericException.builder()
                .httpStatus(unauthorized)
                .errorMessage("Student not found!")
                .build();
    }

    public Boolean existsByUsername(String username) {
        return studentRepository.existsByUsername(username);
    }

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::mapEntityToResponse)
                .toList();
    }

    public void updateStudent(Long studentId, StudentUpdateRequest updateRequest) {
        Student foundedStudent = findStudentById(studentId);

        foundedStudent.setUsername(updateRequest.getUsername());
        foundedStudent.setEmail(updateRequest.getEmail());

        studentRepository.save(foundedStudent);
    }

}
