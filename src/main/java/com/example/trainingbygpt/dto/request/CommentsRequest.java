package com.example.trainingbygpt.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentsRequest extends PageDto {


    public CommentsRequest(int page, int size) {
        super(page, size);
    }
}
