package com.example.prod_ready_features.prod_ready_features.controllers;

import com.example.prod_ready_features.prod_ready_features.dto.PostDTO;
import com.example.prod_ready_features.prod_ready_features.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/posts")
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPosts(){
        return postService.getAllPosts();
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO postDTO){
        return postService.createNewPost(postDTO);
    }

    @GetMapping(path="/{postId}")
    public PostDTO getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PutMapping(path ="/{postId}")
    public PostDTO updatePostById(@RequestBody PostDTO inputPost,@PathVariable Long postId){
        return postService.updatePostById(inputPost,postId);
    }
}
