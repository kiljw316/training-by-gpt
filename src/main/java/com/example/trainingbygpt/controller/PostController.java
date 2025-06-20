package com.example.trainingbygpt.controller;

import com.example.trainingbygpt.dto.request.PostSaveRequest;
import com.example.trainingbygpt.dto.response.ResponseDto;
import com.example.trainingbygpt.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.trainingbygpt.dto.response.ResponseDto.created;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseDto getPosts() {
        return ResponseDto.ok(postService.getPosts());
    }

    @GetMapping("{postId}")
    public ResponseDto getPost(@PathVariable Long postId) {
        return ResponseDto.ok(postService.getPost(postId));
    }

    @PostMapping
    public ResponseDto savePost(@RequestBody @Valid PostSaveRequest request) {
        return created(postService.savePost(request, 1L));
    }
}
