package com.example.blogapp.controller;

import com.example.blogapp.model.CommentRequest;
import com.example.blogapp.model.dto.BlogCommentsDto;
import com.example.blogapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{blog-id}")
    public void addComment(@PathVariable("blog-id") Long blogId, @RequestBody CommentRequest commentRequest) {
        commentService.addComment(blogId,commentRequest);
    }

    @GetMapping("/{blog-id}")
    public ResponseEntity<List<BlogCommentsDto>> getAllBlogComments(@PathVariable("blog-id") Long blogId){
        return ResponseEntity.ok(commentService.getAllBlogComments(blogId));
    }

}
