package com.example.blogapp.model;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBlogRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private LocalDateTime createdAt;
}
