package com.example.trainingbygpt.dto;

import com.example.trainingbygpt.projection.PostProjection;
import lombok.Getter;

@Getter
public class PostDto {

    private final Long postId;
    private final String title;
    private final String content;
    private final Long commentCount;

    private PostDto(Long postId, String title, String content, Long commentCount) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
    }

    public static PostDto from(PostProjection post) {
        return new PostDto(
            post.getPostId(),
            post.getTitle(),
            post.getContent(),
            post.getCommentCount()
        );
    }
}
