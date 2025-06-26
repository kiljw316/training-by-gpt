package com.example.trainingbygpt.entity;

import com.example.trainingbygpt.dto.request.PostUpdateRequest;
import com.example.trainingbygpt.type.PostStatusType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PostStatusType status;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @Builder
    public Post(User writer, String title, String content) {
        this.user = writer;
        this.title = title;
        this.content = content;
        this.status = PostStatusType.ACTIVE;
        this.comments = new ArrayList<>();
    }

    public void update(PostUpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    public void delete() {
        this.status = PostStatusType.DELETED;
    }
}
