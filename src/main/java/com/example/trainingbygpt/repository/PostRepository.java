package com.example.trainingbygpt.repository;

import com.example.trainingbygpt.entity.PostEntity;
import com.example.trainingbygpt.projection.PostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query("""
            SELECT p.postId AS postId, p.title AS title, p.content AS content, COUNT(c.commentId) AS commentCount
            FROM PostEntity p
            LEFT JOIN CommentEntity c ON c.post.postId = p.postId
            GROUP BY p.postId
        """)
    List<PostProjection> findAllBy();
}
