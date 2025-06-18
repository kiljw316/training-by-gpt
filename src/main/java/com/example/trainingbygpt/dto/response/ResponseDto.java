package com.example.trainingbygpt.dto.response;

import lombok.Builder;

import java.util.Collections;

@Builder
public record ResponseDto(int code, String message, Object result) {

    public static ResponseDto ok(Object result) {
        return ResponseDto.builder()
            .code(200)
            .message("success")
            .result(result)
            .build();
    }

    public static ResponseDto created(Object result) {
        return ResponseDto.builder()
            .code(201)
            .message("success")
            .result(result)
            .build();
    }

    public static ResponseDto badRequest(String message) {
        return ResponseDto.builder()
            .code(400)
            .message(message)
            .result(Collections.EMPTY_MAP)
            .build();
    }
}
