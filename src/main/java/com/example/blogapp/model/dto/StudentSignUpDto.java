package com.example.blogapp.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentSignUpDto {

    private Long id;
    private String username;
    private LocalDateTime createdAt;
}
