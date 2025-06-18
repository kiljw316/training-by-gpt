package com.example.trainingbygpt.service;

import com.example.trainingbygpt.dto.PostDto;
import com.example.trainingbygpt.dto.request.PostSaveRequest;
import com.example.trainingbygpt.entity.Post;
import com.example.trainingbygpt.entity.User;
import com.example.trainingbygpt.repository.PostRepository;
import com.example.trainingbygpt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.trainingbygpt.dto.PostDto.from;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<PostDto> getPosts() {
        return postRepository.findAllBy()
            .stream()
            .map(PostDto::from)
            .toList();
    }

    @Transactional
    public PostDto savePost(PostSaveRequest request, Long userId) {
        User writer = userRepository.findById(userId).orElseThrow();
        Post post = postRepository.save(Post.builder()
            .writer(writer)
            .title(request.getTitle())
            .content(request.getContent())
            .build());
        return from(post);
    }
}
