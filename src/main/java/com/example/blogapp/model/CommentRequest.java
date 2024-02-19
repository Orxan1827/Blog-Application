package com.example.blogapp.model;

import com.example.blogapp.entity.Blog;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String content;
}
