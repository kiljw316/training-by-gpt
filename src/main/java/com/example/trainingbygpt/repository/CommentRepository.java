package com.example.trainingbygpt.repository;

import com.example.trainingbygpt.entity.Comment;
import com.example.trainingbygpt.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    int countByPost(Post post);
}
