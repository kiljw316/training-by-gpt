package com.example.trainingbygpt.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostSaveRequest {

    private String title;
    private String content;

    public PostSaveRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
