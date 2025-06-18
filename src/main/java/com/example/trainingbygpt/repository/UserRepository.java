package com.example.trainingbygpt.repository;

import com.example.trainingbygpt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}