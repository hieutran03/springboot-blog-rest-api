package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostServiceImpl implements PostService {
    private PostRepository postRepositorty;
    @Autowired
    public PostServiceImpl(PostRepository postRepositorty) {
        this.postRepositorty = postRepositorty;
    }
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post newPost = postRepositorty.save(post);
        PostDto postReponse = new PostDto();
        postReponse.setId(newPost.getId());
        postReponse.setTitle(newPost.getTitle());
        postReponse.setDescription(newPost.getDescription());
        postReponse.setContent(newPost.getContent());
        return postReponse;
    }
}
