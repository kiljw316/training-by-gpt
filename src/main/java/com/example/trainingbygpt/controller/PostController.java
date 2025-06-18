package com.example.trainingbygpt.controller;

import com.example.trainingbygpt.dto.PostDto;
import com.example.trainingbygpt.dto.request.PostSaveRequest;
import com.example.trainingbygpt.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDto> getPosts() {
        return postService.getPosts();
    }

    @PostMapping
    public PostDto savePost(@RequestBody @Valid PostSaveRequest request) {
        return postService.savePost(request, 1L);
    }
}
