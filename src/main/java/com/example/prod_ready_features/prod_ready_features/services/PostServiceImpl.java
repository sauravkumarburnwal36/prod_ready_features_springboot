package com.example.prod_ready_features.prod_ready_features.services;

import com.example.prod_ready_features.prod_ready_features.dto.PostDTO;
import com.example.prod_ready_features.prod_ready_features.entities.PostEntity;
import com.example.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import com.example.prod_ready_features.prod_ready_features.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream().map(
                postEntity -> modelMapper.map(
                        postEntity, PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post with id: "+postId+" not found"));
        return modelMapper.map(postEntity,PostDTO.class);
    }

    @Override
    public PostDTO createNewPost(PostDTO postDTO) {
        PostEntity postEntity=modelMapper.map(postDTO,PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity),PostDTO.class);
    }

    @Override
    public PostDTO updatePostById(PostDTO inputPost, Long postId) {
        PostEntity olderPost=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException(
                "Post with id: "+postId+" not found"));
        inputPost.setPostId(postId);
        modelMapper.map(inputPost,olderPost);
        return modelMapper.map(postRepository.save(olderPost),PostDTO.class);
    }
}
