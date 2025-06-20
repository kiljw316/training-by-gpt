package com.example.trainingbygpt.service;

import com.example.trainingbygpt.dto.PostDetailDto;
import com.example.trainingbygpt.dto.PostDto;
import com.example.trainingbygpt.dto.request.PostSaveRequest;
import com.example.trainingbygpt.entity.Post;
import com.example.trainingbygpt.entity.User;
import com.example.trainingbygpt.projection.PostProjection;
import com.example.trainingbygpt.repository.PostRepository;
import com.example.trainingbygpt.repository.UserRepository;
import com.example.trainingbygpt.type.RoleType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

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
        String username = "테스트 유저";

        User writer = User.builder().username(username).role(RoleType.USER).build();
        when(userRepository.findById(userId)).thenReturn(Optional.of(writer));
        Post post = Post.builder()
            .writer(writer)
            .title(title)
            .content(content)
            .build();
        when(postRepository.save(any())).thenReturn(post);

        // when
        PostDetailDto result = postService.savePost(request, userId);

        // then
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getContent()).isEqualTo(content);
        assertThat(result.getUsername()).isEqualTo(username);
        assertThat(result.getComments()).hasSize(0);
    }

    @Test
    void 게시글_목록_조회_성공() {
        // given
        when(postRepository.findAllBy()).thenReturn(createPosts());

        // when
        List<PostDto> posts = postService.getPosts();

        // then
        assertThat(posts).hasSize(3);

        PostDto post = posts.get(0);
        assertThat(post.getPostId()).isEqualTo(1L);
        assertThat(post.getTitle()).isEqualTo("제목1");
        assertThat(post.getContent()).isEqualTo("내용1");
        assertThat(post.getCommentCount()).isEqualTo(1);
    }

    @Test
    void 게시글_조회_성공() {
        // given
        String username = "유저 이름";

        User writer = User.builder().username(username).role(RoleType.USER).build();
        Post post = Post.builder().title("게시글 제목").content("게시글 내용").writer(writer).build();
        when(postRepository.findById(any())).thenReturn(Optional.of(post));

        // when
        PostDetailDto result = postService.getPost(1L);

        // then
        assertThat(result.getTitle()).isEqualTo("게시글 제목");
        assertThat(result.getContent()).isEqualTo("게시글 내용");
        assertThat(result.getUsername()).isEqualTo(username);

        // TODO 게시글 조회 테스트 방법 고민
//        assertThat(result.getComments()).hasSize(1);
    }

    private List<PostProjection> createPosts() {
        List<PostProjection> posts = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            posts.add(new PostProjection() {
                @Override
                public Long getPostId() {
                    return (long) finalI;
                }

                @Override
                public String getTitle() {
                    return "제목" + finalI;
                }

                @Override
                public String getContent() {
                    return "내용" + finalI;
                }

                @Override
                public Integer getCommentCount() {
                    return finalI;
                }
            });
        }
        return posts;
    }
}