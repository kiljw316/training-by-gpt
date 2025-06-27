package com.example.trainingbygpt.controller;

import com.example.trainingbygpt.dto.request.CommentSaveRequest;
import com.example.trainingbygpt.dto.response.ResponseDto;
import com.example.trainingbygpt.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.trainingbygpt.dto.response.ResponseDto.created;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("{commentId}/replies")
    public ResponseDto saveReply(@PathVariable Long commentId,
                                 @RequestBody @Valid CommentSaveRequest request) {
        return created(commentService.saveReply(request, 1L, commentId));
    }
}
