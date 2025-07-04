package com.example.trainingbygpt.repository;

import com.example.trainingbygpt.entity.Like;
import com.example.trainingbygpt.entity.User;
import com.example.trainingbygpt.type.TargetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndTargetTypeAndTargetId(User user, TargetType targetType, Long targetId);

    Optional<Like> findByUserAndTargetTypeAndTargetId(User user, TargetType targetType, Long targetId);
}