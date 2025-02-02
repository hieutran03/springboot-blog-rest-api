package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public BlogAPIException() {
        super();
    }

    public BlogAPIException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
