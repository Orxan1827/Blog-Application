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
public class BlogDetailsDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private StudentDto author;
}
