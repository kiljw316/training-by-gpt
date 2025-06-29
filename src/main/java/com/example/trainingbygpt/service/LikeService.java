package com.example.trainingbygpt.service;

import com.example.trainingbygpt.entity.Like;
import com.example.trainingbygpt.entity.User;
import com.example.trainingbygpt.repository.LikeRepository;
import com.example.trainingbygpt.repository.UserRepository;
import com.example.trainingbygpt.type.TargetType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    @Transactional
    public void likePost(Long userId, Long postId) {
        User user = userRepository.findById(userId).orElseThrow();
        boolean exists = likeRepository.existsByUserAndTargetTypeAndTargetId(user, TargetType.POST, postId);
        if (exists) {
            throw new IllegalArgumentException("already liked post");
        }
        likeRepository.save(Like.ofPost(user, postId));
    }
}
