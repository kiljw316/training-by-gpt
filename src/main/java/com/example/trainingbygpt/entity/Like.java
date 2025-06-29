package com.example.trainingbygpt.entity;

import com.example.trainingbygpt.type.TargetType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "likes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like extends BaseEntity {

    @Id
    @Column(name = "like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_type")
    private TargetType targetType;

    @Column(name = "target_id")
    private Long targetId;

    public static Like ofPost(User user, Long postId) {
        return Like.builder()
            .user(user)
            .targetType(TargetType.POST)
            .targetId(postId)
            .build();
    }

    @Builder
    private Like(User user, TargetType targetType, Long targetId) {
        this.user = user;
        this.targetType = targetType;
        this.targetId = targetId;
    }
}
