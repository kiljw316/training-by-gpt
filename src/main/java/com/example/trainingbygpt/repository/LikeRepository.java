package com.example.trainingbygpt.repository;

import com.example.trainingbygpt.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}