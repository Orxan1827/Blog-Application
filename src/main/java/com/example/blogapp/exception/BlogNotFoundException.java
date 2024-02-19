package com.example.blogapp.exception;

import static com.example.blogapp.enums.ErrorCode.BLOG_NOT_FOUND;

public class BlogNotFoundException extends GenericException{

    public BlogNotFoundException() {
        super(BLOG_NOT_FOUND.getStatus(), BLOG_NOT_FOUND.getCode(), BLOG_NOT_FOUND.getMessage());
    }
}
