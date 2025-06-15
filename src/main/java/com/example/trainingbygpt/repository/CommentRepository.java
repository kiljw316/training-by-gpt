package com.example.trainingbygpt.repository;

import com.example.trainingbygpt.entity.CommentEntity;
import com.example.trainingbygpt.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    int countByPost(PostEntity post);
}
