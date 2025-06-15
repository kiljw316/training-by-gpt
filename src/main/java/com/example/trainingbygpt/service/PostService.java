package com.example.trainingbygpt.service;

import com.example.trainingbygpt.dto.PostDto;
import com.example.trainingbygpt.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<PostDto> getPosts() {
        return postRepository.findAllBy()
            .stream()
            .map(PostDto::from)
            .toList();
    }
}
