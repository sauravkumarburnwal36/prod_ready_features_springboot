package com.example.prod_ready_features.prod_ready_features.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {

    private Long postId;
    private String title;
    private String description;
}
