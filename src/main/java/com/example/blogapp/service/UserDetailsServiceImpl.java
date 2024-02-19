package com.example.blogapp.service;

import com.example.blogapp.entity.Student;
import com.example.blogapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by username: " + username));
        return new org.springframework.security.core.userdetails.User(student.getUsername(), student.getPassword(), new ArrayList<>());
    }
}
