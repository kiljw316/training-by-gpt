package com.example.trainingbygpt.service;

import com.example.trainingbygpt.dto.PostDto;
import com.example.trainingbygpt.dto.request.PostSaveRequest;
import com.example.trainingbygpt.entity.Post;
import com.example.trainingbygpt.entity.User;
import com.example.trainingbygpt.repository.PostRepository;
import com.example.trainingbygpt.repository.UserRepository;
import com.example.trainingbygpt.type.RoleType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void 게시글_작성_성공() {
        // given
        String title = "게시글 제목";
        String content = "게시글 내용";
        PostSaveRequest request = new PostSaveRequest(title, content);
        Long userId = 100L;

        User writer = User.builder().username("테스트 유저").role(RoleType.USER).build();
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(writer));
        Post post = Post.builder()
            .writer(writer)
            .title(title)
            .content(content)
            .build();
        Mockito.when(postRepository.save(Mockito.any())).thenReturn(post);

        // when
        PostDto result = postService.savePost(request, userId);

        // then
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getContent()).isEqualTo(content);
        assertThat(result.getCommentCount()).isEqualTo(0);
    }
}