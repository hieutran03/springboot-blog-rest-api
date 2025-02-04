package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("posts/{postId}/comments")
    ResponseEntity<CommentDto> createComment(
            @PathVariable(name = "postId") Long postId,
            @Valid @RequestBody CommentDto commentDto
    ){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("posts/{postId}/comments")
    List<CommentDto> getCommentsByPostId(@PathVariable(name="postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("posts/{postId}/comments/{commentId}")
    ResponseEntity<CommentDto> getCommentById(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "commentId") Long commentId
    ){
        return new ResponseEntity<>(commentService.getCommentById(postId,commentId), HttpStatus.OK);
    }

    @PutMapping("posts/{postId}/comments/{commentId}")
    ResponseEntity<CommentDto> updateComment(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "commentId") Long commentId,
            @Valid @RequestBody CommentDto commentDto
    ){
        return new ResponseEntity<>(commentService.updateComment(postId,commentId, commentDto), HttpStatus.OK);
    }

    @DeleteMapping("posts/{postId}/comments/{commentId}")
    ResponseEntity<String> deleteComment(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "commentId") Long commentId
    ){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
