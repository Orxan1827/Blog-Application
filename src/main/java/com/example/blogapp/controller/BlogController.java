package com.example.blogapp.controller;

import com.example.blogapp.model.CreateBlogRequest;
import com.example.blogapp.model.UpdateBlogRequest;
import com.example.blogapp.model.dto.BlogCommentsDto;
import com.example.blogapp.model.dto.BlogDetailsDto;
import com.example.blogapp.model.dto.BlogDto;
import com.example.blogapp.service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/{student-id}")
    public void createBlog(@Valid @PathVariable("student-id") Long studentId, @RequestBody CreateBlogRequest createRequest) {
        blogService.createBlog(studentId, createRequest);
    }

    @GetMapping("/{blog-id}")
    public ResponseEntity<BlogDto> getBlog(@PathVariable("blog-id") Long blogId) {
        return ResponseEntity.ok(blogService.getBlog(blogId));
    }

    @GetMapping
    public ResponseEntity<List<BlogDto>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @GetMapping("/details")
    public ResponseEntity<List<BlogDetailsDto>> getAllBlogWithDetails(){
        return ResponseEntity.ok(blogService.getAllBlogWithDetails());
    }

    @PutMapping("/{blog-id}")
    public void updateBlog(@Valid @PathVariable("blog-id") Long blogId, @RequestBody UpdateBlogRequest updateRequest) {
        blogService.updateBlog(blogId, updateRequest);
    }

    @DeleteMapping("/{blog-id}")
    public void deleteBlog(@PathVariable("blog-id") Long blogId) {
        blogService.deleteBlog(blogId);
    }

}
