package com.example.trainingbygpt.repository;

import com.example.trainingbygpt.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
