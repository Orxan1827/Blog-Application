package com.example.blogapp.repository;

import com.example.blogapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUsername(String username);

    Boolean existsByUsername(String username);

    Optional<Student> findStudentByEmail(String email);
}
