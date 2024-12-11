package com.example.prod_ready_features.prod_ready_features.services;

import com.example.prod_ready_features.prod_ready_features.dto.PostDTO;
import java.util.*;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO getPostById(Long postId);

    PostDTO createNewPost(PostDTO PostDTO);

    PostDTO updatePostById(PostDTO inputPost, Long postId);
}
