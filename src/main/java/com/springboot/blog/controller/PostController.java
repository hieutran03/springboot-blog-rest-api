package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(this.postService.createPost(postDto),HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDto> getAllPost(){
        return postService.getAllPosts();
    }
//    @GetMapping
//    public ResponseEntity<List<PostDto>> getAllPost(){
//        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
//    }

}
