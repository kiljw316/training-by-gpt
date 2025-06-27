package com.example.trainingbygpt.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentSaveRequest {
    @Size(max = 1000)
    private String content;

    public CommentSaveRequest(String content) {
        this.content = content;
    }
}
