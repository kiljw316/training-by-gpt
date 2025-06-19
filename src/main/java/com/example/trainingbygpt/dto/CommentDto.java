package com.example.trainingbygpt.dto;

import com.example.trainingbygpt.entity.Comment;
import lombok.Getter;

@Getter
public class CommentDto {
    private final Long commentId;
    private final String content;
    private final Long parentId;
    private final Long userId;
    private final String username;

    public static CommentDto from(Comment comment) {
        return new CommentDto(
            comment.getCommentId(),
            comment.getContent(),
            comment.getParentId(),
            comment.getUser().getUserId(),
            comment.getUser().getUsername()
        );
    }

    private CommentDto(Long commentId, String content, Long parentId, Long userId, String username) {
        this.commentId = commentId;
        this.content = content;
        this.parentId = parentId;
        this.userId = userId;
        this.username = username;
    }
}
