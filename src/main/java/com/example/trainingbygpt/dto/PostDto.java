package com.example.trainingbygpt.dto;

import com.example.trainingbygpt.entity.PostEntity;
import lombok.Getter;

@Getter
public class PostDto {

    private final Long postId;
    private final String title;
    private final String content;
    private final Integer commentCount;

    private PostDto(Long postId, String title, String content, Integer commentCount) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
    }

    public static PostDto from(PostEntity post, int commentCount) {
        return new PostDto(
            post.getPostId(),
            post.getTitle(),
            post.getContent(),
            commentCount
        );
    }
}
