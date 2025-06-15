package com.example.trainingbygpt.service;

import com.example.trainingbygpt.dto.PostDto;
import com.example.trainingbygpt.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentService commentService;

    @Transactional(readOnly = true)
    public List<PostDto> getPosts() {
        return postRepository.findAll(PageRequest.of(0, 100))
            .stream()
            .map(post -> PostDto.from(post, commentService.getCommentCount(post)))
            .toList();
    }
}
