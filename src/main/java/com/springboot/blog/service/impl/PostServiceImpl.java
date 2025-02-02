package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundExeception;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
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
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepositorty.findAll(pageable);

        List<Post> listOfPost = posts.getContent();
        List<PostDto> content = listOfPost.stream().map(post-> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(pageNo);
        postResponse.setPageSiae(pageSize);
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id){
        Post post = postRepositorty.findById(id).orElseThrow(
                ()-> new ResourceNotFoundExeception(
                        "Post",
                        "id",
                        Long.toString(id)
                )
        );
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id){
        Post post = postRepositorty.findById(id).orElseThrow(
                ()-> new ResourceNotFoundExeception(
                        "Post",
                        "id",
                        Long.toString(id)
                )
        );

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        Post updatedfPost = postRepositorty.save(post);
        return mapToDto(updatedfPost);
    }

    @Override
    public void deletePostById(Long id){
        Post post = postRepositorty.findById(id).orElseThrow(
                ()-> new ResourceNotFoundExeception(
                        "Post",
                        "id",
                        Long.toString(id)
                )
        );
        postRepositorty.delete(post);
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
