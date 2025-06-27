package com.example.trainingbygpt.service;

import com.example.trainingbygpt.dto.CommentDto;
import com.example.trainingbygpt.dto.request.CommentSaveRequest;
import com.example.trainingbygpt.entity.Comment;
import com.example.trainingbygpt.repository.CommentRepository;
import com.example.trainingbygpt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentDto saveReply(CommentSaveRequest request, Long userId, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        Comment reply = commentRepository.save(Comment.builder()
            .user(userRepository.findById(userId).orElseThrow())
            .post(comment.getPost())
            .content(request.getContent())
            .parentId(commentId)
            .build());
        return CommentDto.from(reply);
    }
}
