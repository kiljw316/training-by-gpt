package com.example.trainingbygpt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("posts")
class PostController {
    @GetMapping
    public List<Object> getPosts() {
        return List.of();
    }
}
