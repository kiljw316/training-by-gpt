package com.example.trainingbygpt.service;

import com.example.trainingbygpt.entity.PostEntity;
import com.example.trainingbygpt.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Cacheable(value = "commentCount", key = "#post.postId")
    @Transactional(readOnly = true)
    public int getCommentCount(PostEntity post) {
        return commentRepository.countByPost(post);
    }
}
