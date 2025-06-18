package com.example.trainingbygpt.repository;

import com.example.trainingbygpt.entity.Post;
import com.example.trainingbygpt.projection.PostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
            SELECT p.postId AS postId, p.title AS title, p.content AS content, COUNT(c.commentId) AS commentCount
            FROM Post p
            LEFT JOIN Comment c ON c.post.postId = p.postId
            GROUP BY p.postId
        """)
    List<PostProjection> findAllBy();
}
