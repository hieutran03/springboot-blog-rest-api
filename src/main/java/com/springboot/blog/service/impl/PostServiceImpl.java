package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepositorty;
    @Autowired
    public PostServiceImpl(PostRepository postRepositorty) {
        this.postRepositorty = postRepositorty;
    }
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);

        Post newPost = postRepositorty.save(post);
        PostDto postReponse = mapToDto(newPost);

        return postReponse;
    }

    @Override
    public List<PostDto> getAllPosts(){
        List<Post> posts = postRepositorty.findAll();
        return posts.stream().map(post-> mapToDto(post)).collect(Collectors.toList());
    }

    // Convert Entity to Dto
    public PostDto mapToDto(Post post){
        PostDto postReponse = new PostDto();
        postReponse.setId(post.getId());
        postReponse.setTitle(post.getTitle());
        postReponse.setDescription(post.getDescription());
        postReponse.setContent(post.getContent());
        return postReponse;
    }

    // COnvert Dto to Entity
    public Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
