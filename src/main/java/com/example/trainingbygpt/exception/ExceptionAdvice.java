package com.example.trainingbygpt.exception;

import com.example.trainingbygpt.dto.response.ResponseDto;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static com.example.trainingbygpt.dto.response.ResponseDto.badRequest;

@RestControllerAdvice
class ExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return badRequest(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage());
    }
}
