package com.example.blogapp.service;

import com.example.blogapp.entity.Blog;
import com.example.blogapp.entity.Comment;
import com.example.blogapp.exception.BlogNotFoundException;
import com.example.blogapp.mapper.BlogMapper;
import com.example.blogapp.mapper.CommentMapper;
import com.example.blogapp.model.CreateBlogRequest;
import com.example.blogapp.model.UpdateBlogRequest;
import com.example.blogapp.model.dto.BlogCommentsDto;
import com.example.blogapp.model.dto.BlogDetailsDto;
import com.example.blogapp.model.dto.BlogDto;
import com.example.blogapp.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final StudentService studentService;
    private final BlogMapper blogMapper;

    protected Blog findBlogById(Long BlogId) {
        return blogRepository.findById(BlogId).orElseThrow(BlogNotFoundException::new);
    }

    public BlogDto getBlog(Long blogId) {
        return blogMapper.mapEntityToResponse(findBlogById(blogId));
    }

    @Transactional
    public void createBlog(Long studentId, CreateBlogRequest createRequest) {
        var author = studentService.findStudentById(studentId);
        var blog = Blog.builder()
                .title(createRequest.getTitle())
                .content(createRequest.getContent())
                .author(author)
                .createdAt(LocalDateTime.now())
                .build();
        blogRepository.save(blog);
    }

    public List<BlogDto> getAllBlogs() {
        return blogRepository.findAll()
                .stream()
                .map(blogMapper::mapEntityToResponse)
                .toList();
    }

    public List<BlogDetailsDto> getAllBlogWithDetails() {
        return blogRepository.findAll()
                .stream()
                .map(blogMapper::mapEntityToBlogDetailsResponse)
                .toList();
    }

    public void updateBlog(Long blogId, UpdateBlogRequest updateRequest) {
        var blogById = findBlogById(blogId);
        if (updateRequest.getTitle() != null)
            blogById.setTitle(updateRequest.getTitle());
        if (updateRequest.getContent() != null)
            blogById.setContent(updateRequest.getContent());
        blogRepository.save(blogById);
    }

    public void deleteBlog(Long blogId) {
        findBlogById(blogId);
        blogRepository.deleteById(blogId);
    }

}
