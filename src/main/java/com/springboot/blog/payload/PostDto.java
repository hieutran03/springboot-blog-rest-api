package com.springboot.blog.payload;

import lombok.Data;

import java.util.List;

@Data
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;
    List <CommentDto> comments;
}
