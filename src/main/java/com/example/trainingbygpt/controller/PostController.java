package com.example.trainingbygpt.controller;

import com.example.trainingbygpt.dto.PostDto;
import com.example.trainingbygpt.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
