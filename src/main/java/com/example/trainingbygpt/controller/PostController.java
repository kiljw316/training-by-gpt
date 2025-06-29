package com.example.trainingbygpt.controller;

import com.example.trainingbygpt.dto.request.CommentSaveRequest;
import com.example.trainingbygpt.dto.request.CommentsRequest;
import com.example.trainingbygpt.dto.request.PostSaveRequest;
import com.example.trainingbygpt.dto.request.PostUpdateRequest;
import com.example.trainingbygpt.dto.response.ResponseDto;
import com.example.trainingbygpt.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.trainingbygpt.dto.response.ResponseDto.*;

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

    @PutMapping("{postId}")
    public ResponseDto updatePost(@PathVariable Long postId,
                                  @RequestBody @Valid PostUpdateRequest request) {
        return ok(postService.updatePost(postId, request));
    }

    @DeleteMapping("{postId}")
    public ResponseDto deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return noContent();
    }

    @PostMapping("{postId}/comments")
    public ResponseDto saveComment(@PathVariable Long postId,
                                   @RequestBody @Valid CommentSaveRequest request) {
        return created(postService.saveComment(request, 1L, postId));
    }

    @GetMapping("{postId}/comments")
    public ResponseDto getComments(@PathVariable Long postId,
                                   @RequestBody @Valid CommentsRequest request) {
        return ok(postService.getComments(request, postId));
    }

    @PostMapping("{postId}/likes")
    public ResponseDto likePost(@PathVariable Long postId) {
        postService.likePost(1L, postId);
        return noContent();
    }

    @DeleteMapping("{postId}/likes")
    public ResponseDto unlikePost(@PathVariable Long postId) {
        postService.unlikePost(1L, postId);
        return noContent();
    }
}
