package com.example.trainingbygpt.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class PageDto {
    private int page;
    private int size;

    public PageDto(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
