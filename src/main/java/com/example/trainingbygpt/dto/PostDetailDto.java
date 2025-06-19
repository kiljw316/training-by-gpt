package com.example.trainingbygpt.dto;

import com.example.trainingbygpt.entity.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostDetailDto {

    private final Long postId;
    private final String title;
    private final String content;

    private final Long userId;
    private final String username;

    private final List<CommentDto> comments;

    public static PostDetailDto from(Post post) {
        return new PostDetailDto(
            post.getPostId(),
            post.getTitle(),
            post.getContent(),
            post.getUser().getUserId(),
            post.getUser().getUsername(),
            post.getComments().stream().map(CommentDto::from).toList()
        );
    }

    private PostDetailDto(Long postId, String title, String content, Long userId, String username, List<CommentDto> comments) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.username = username;
        this.comments = comments;
    }
}
