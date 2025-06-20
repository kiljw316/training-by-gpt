package com.example.trainingbygpt.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUpdateRequest {

    @NotBlank
    private String title;

    @Size(max = 1000)
    private String content;

    public PostUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
