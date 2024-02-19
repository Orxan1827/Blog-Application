package com.example.blogapp.service;

import com.example.blogapp.entity.Blog;
import com.example.blogapp.entity.Comment;
import com.example.blogapp.mapper.CommentMapper;
import com.example.blogapp.model.CommentRequest;
import com.example.blogapp.model.dto.BlogCommentsDto;
import com.example.blogapp.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BlogService blogService;

    private final CommentMapper commentMapper;
    @Transactional
    public void addComment(Long blogId, CommentRequest commentRequest){

        var blogToComment = blogService.findBlogById(blogId);
        var comment = Comment.builder()
                .name(commentRequest.getName())
                .email(commentRequest.getEmail())
                .content(commentRequest.getContent())
                .blog(blogToComment)
                .build();
        blogToComment.getComments().add(comment);
        commentRepository.save(comment);
    }

    public List<BlogCommentsDto> getAllBlogComments(Long blogId) {
        var blog = blogService.findBlogById(blogId);
        return blog.getComments()
                .stream()
                .map(commentMapper::mapEntityToBlogCommentsDto)
                .toList();
    }
}
