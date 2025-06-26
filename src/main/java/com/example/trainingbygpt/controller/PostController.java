package com.example.trainingbygpt.controller;

import com.example.trainingbygpt.dto.request.PostSaveRequest;
import com.example.trainingbygpt.dto.request.PostUpdateRequest;
import com.example.trainingbygpt.dto.response.ResponseDto;
import com.example.trainingbygpt.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.trainingbygpt.dto.response.ResponseDto.created;
import static com.example.trainingbygpt.dto.response.ResponseDto.ok;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseDto savePost(@RequestBody @Valid PostSaveRequest request) {
        return created(postService.savePost(request, 1L));
    }

    @GetMapping
    public ResponseDto getPosts() {
        return ok(postService.getPosts());
    }

    @GetMapping("{postId}")
    public ResponseDto getPost(@PathVariable Long postId) {
        return ok(postService.getPost(postId));
    }

    @PostMapping
    public ResponseDto savePost(@RequestBody @Valid PostSaveRequest request) {
        return created(postService.savePost(request, 1L));
    }

    @PutMapping("{postId}")
    public ResponseDto updatePost(@PathVariable Long postId,
                                  @RequestBody @Valid PostUpdateRequest request) {
        return ok(postService.updatePost(postId, request));
    }
}
