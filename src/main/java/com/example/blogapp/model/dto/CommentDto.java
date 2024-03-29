package com.example.blogapp.model.dto;

import com.example.blogapp.entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;
    private String name;
    private String email;
    private String content;
    private Blog blog;
}
