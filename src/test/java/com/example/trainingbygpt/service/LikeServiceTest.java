package com.example.trainingbygpt.service;

import com.example.trainingbygpt.entity.User;
import com.example.trainingbygpt.repository.LikeRepository;
import com.example.trainingbygpt.repository.UserRepository;
import com.example.trainingbygpt.type.RoleType;
import com.example.trainingbygpt.type.TargetType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private LikeRepository likeRepository;

    @InjectMocks
    private LikeService likeService;

    @Test
    void 게시글_좋아요_성공() {
        // given
        Long userId = 1L;
        Long postId = 1L;

        User user = User.builder().username("홍길동").role(RoleType.USER).build();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(likeRepository.existsByUserAndTargetTypeAndTargetId(user, TargetType.POST, postId)).thenReturn(false);
        
        // when - then
        assertDoesNotThrow(() -> likeService.likePost(userId, postId));
    }

    @Test
    void 게시글_좋아요_실패_이미_좋아요_누름() {
        Long userId = 1L;
        Long postId = 1L;

        User user = User.builder().username("홍길동").role(RoleType.USER).build();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(likeRepository.existsByUserAndTargetTypeAndTargetId(user, TargetType.POST, postId)).thenReturn(true);

        // when - then
        assertThatThrownBy(() -> likeService.likePost(userId, postId))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("already liked post");
    }
}