package com.example.trainingbygpt.service;

import com.example.trainingbygpt.dto.CommentDto;
import com.example.trainingbygpt.dto.request.CommentSaveRequest;
import com.example.trainingbygpt.entity.Comment;
import com.example.trainingbygpt.entity.Post;
import com.example.trainingbygpt.entity.User;
import com.example.trainingbygpt.repository.CommentRepository;
import com.example.trainingbygpt.repository.UserRepository;
import com.example.trainingbygpt.type.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;


    private final String username = "홍길동";

    private final Long commentId = 1L;

    private User writer;
    private Comment comment;

    @BeforeEach
    void setUp() {
        writer = User.builder().username(username).role(RoleType.USER).build();
        Post post = Post.builder().title("게시글 제목").content("게시글 내용").writer(writer).build();
        comment = Comment.builder().commentId(commentId).user(writer).post(post).content("댓글 내용").build();
    }

    @Test
    void 대댓글_작성_성공() {
        // given
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(writer));
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(comment));

        String replyContent = "대댓글 내용";
        Comment reply = Comment.builder()
            .content(replyContent)
            .user(writer)
            .parentId(commentId)
            .build();

        when(commentRepository.save(any())).thenReturn(reply);

        CommentSaveRequest request = new CommentSaveRequest(replyContent);

        // when
        CommentDto result = commentService.saveReply(request, 1L, commentId);

        // then
        assertThat(result.getUsername()).isEqualTo(username);
        assertThat(result.getContent()).isEqualTo(replyContent);
        assertThat(result.getParentId()).isEqualTo(commentId);
    }
}