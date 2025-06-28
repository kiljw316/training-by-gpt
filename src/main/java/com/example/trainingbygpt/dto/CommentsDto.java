package com.example.trainingbygpt.dto;

import com.example.trainingbygpt.entity.Comment;
import lombok.Getter;

import java.util.List;

@Getter
public class CommentsDto {

    private final List<CommentDto> comments;
    private final Long totalCount;

    public static CommentsDto from(List<Comment> comments, Long totalCount) {
        return new CommentsDto(comments.stream().map(CommentDto::from).toList(), totalCount);
    }

    private CommentsDto(List<CommentDto> comments, Long totalCount) {
        this.comments = comments;
        this.totalCount = totalCount;
    }
}
