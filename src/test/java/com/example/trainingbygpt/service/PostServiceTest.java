package com.example.trainingbygpt.service;

import com.example.trainingbygpt.dto.CommentDto;
import com.example.trainingbygpt.dto.PostDetailDto;
import com.example.trainingbygpt.dto.PostDto;
import com.example.trainingbygpt.dto.request.CommentSaveRequest;
import com.example.trainingbygpt.dto.request.PostSaveRequest;
import com.example.trainingbygpt.dto.request.PostUpdateRequest;
import com.example.trainingbygpt.entity.Comment;
import com.example.trainingbygpt.entity.Post;
import com.example.trainingbygpt.entity.User;
import com.example.trainingbygpt.projection.PostProjection;
import com.example.trainingbygpt.repository.CommentRepository;
import com.example.trainingbygpt.repository.PostRepository;
import com.example.trainingbygpt.repository.UserRepository;
import com.example.trainingbygpt.type.PostStatusType;
import com.example.trainingbygpt.type.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private PostService postService;

    private final String title = "게시글 제목";
    private final String postContent = "게시글 내용";
    private final String username = "홍길동";
    private Post post;
    private User writer;

    @BeforeEach
    void setUp() {
        writer = User.builder().username(username).role(RoleType.USER).build();
        post = Post.builder().title(title).content(postContent).writer(writer).build();
    }

    @Test
    void 게시글_목록_조회_성공() {
        // given
        when(postRepository.findAllByStatus(PostStatusType.ACTIVE)).thenReturn(createPosts());

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
        Long postId = 1L;
        when(postRepository.findByPostIdAndStatus(postId, PostStatusType.ACTIVE)).thenReturn(Optional.of(post));

        // when
        PostDetailDto result = postService.getPost(postId);

        // then
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getContent()).isEqualTo(postContent);
        assertThat(result.getUsername()).isEqualTo(username);

        // TODO 게시글 조회 테스트 방법 고민
//        assertThat(result.getComments()).hasSize(1);
    }

    @Test
    void 게시글_작성_성공() {
        // given
        PostSaveRequest request = new PostSaveRequest(title, postContent);
        Long userId = 100L;

        when(userRepository.findById(userId)).thenReturn(Optional.of(writer));
        when(postRepository.save(any())).thenReturn(post);

        // when
        PostDetailDto result = postService.savePost(request, userId);

        // then
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getContent()).isEqualTo(postContent);
        assertThat(result.getUsername()).isEqualTo(username);
        assertThat(result.getComments()).hasSize(0);
    }

    @Test
    void 게시글_수정_성공() {
        // given
        PostUpdateRequest request = new PostUpdateRequest("변경된 제목", "변경된 내용");
        Long postId = 100L;

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        // when
        postService.updatePost(postId, request);

        // then
        assertThat(post.getTitle()).isEqualTo("변경된 제목");
        assertThat(post.getContent()).isEqualTo("변경된 내용");
    }

    @Test
    void 게시글_삭제_실패_존재하지_않는_게시글() {
        // given
        when(postRepository.findById(any())).thenReturn(Optional.empty());

        // when - then
        assertThatThrownBy(() -> postService.deletePost(1L))
            .isExactlyInstanceOf(NoSuchElementException.class);
    }

    @Test
    void 게시글_삭제_성공() {
        // given
        Long postId = 100L;

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        // when
        postService.deletePost(postId);

        // then
        assertThat(post.getStatus()).isEqualTo(PostStatusType.DELETED);
    }

    @Test
    void 댓글_작성_성공() {
        // given
        String commentContent = "댓글 내용";
        CommentSaveRequest request = new CommentSaveRequest(commentContent);

        when(userRepository.findById(any())).thenReturn(Optional.of(writer));
        when(postRepository.findById(any())).thenReturn(Optional.of(post));
        Comment comment = Comment.builder()
            .post(post)
            .user(writer)
            .content(commentContent)
            .build();
        when(commentRepository.save(any())).thenReturn(comment);

        // when
        CommentDto commentDto = postService.saveComment(request, 1L, 1L);

        // then
        assertThat(commentDto.getContent()).isEqualTo(commentContent);
        assertThat(commentDto.getUsername()).isEqualTo(username);
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