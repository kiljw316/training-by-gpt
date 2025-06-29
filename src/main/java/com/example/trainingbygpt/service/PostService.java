package com.example.trainingbygpt.service;

import com.example.trainingbygpt.dto.CommentDto;
import com.example.trainingbygpt.dto.CommentsDto;
import com.example.trainingbygpt.dto.PostDetailDto;
import com.example.trainingbygpt.dto.PostDto;
import com.example.trainingbygpt.dto.request.CommentSaveRequest;
import com.example.trainingbygpt.dto.request.CommentsRequest;
import com.example.trainingbygpt.dto.request.PostSaveRequest;
import com.example.trainingbygpt.dto.request.PostUpdateRequest;
import com.example.trainingbygpt.entity.Comment;
import com.example.trainingbygpt.entity.Post;
import com.example.trainingbygpt.entity.User;
import com.example.trainingbygpt.repository.CommentRepository;
import com.example.trainingbygpt.repository.LikeRepository;
import com.example.trainingbygpt.repository.PostRepository;
import com.example.trainingbygpt.repository.UserRepository;
import com.example.trainingbygpt.type.PostStatusType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    private final LikeService likeService;

    @Transactional(readOnly = true)
    public List<PostDto> getPosts() {
        return postRepository.findAllByStatus(PostStatusType.ACTIVE)
            .stream()
            .map(PostDto::from)
            .toList();
    }

    @Transactional(readOnly = true)
    public PostDetailDto getPost(Long postId) {
        return postRepository.findByPostIdAndStatus(postId, PostStatusType.ACTIVE)
            .map(PostDetailDto::from)
            .orElseThrow();
    }

    @Transactional
    public PostDetailDto savePost(PostSaveRequest request, Long userId) {
        User writer = userRepository.findById(userId).orElseThrow();
        Post post = postRepository.save(Post.builder()
            .writer(writer)
            .title(request.getTitle())
            .content(request.getContent())
            .build());
        return PostDetailDto.from(post);
    }

    @Transactional
    public PostDetailDto updatePost(Long postId, PostUpdateRequest request) {
        Post post = postRepository.findById(postId).orElseThrow();
        post.update(request);
        return PostDetailDto.from(post);
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        post.delete();
    }

    @Transactional
    public CommentDto saveComment(CommentSaveRequest request, Long userId, Long postId) {
        Comment comment = commentRepository.save(Comment.builder()
            .user(userRepository.findById(userId).orElseThrow())
            .post(postRepository.findById(postId).orElseThrow())
            .content(request.getContent())
            .build());

        return CommentDto.from(comment);
    }

    @Transactional(readOnly = true)
    public CommentsDto getComments(CommentsRequest request, Long postId) {
        // TODO 대댓글 구성
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());
        Post post = postRepository.findById(postId).orElseThrow();
        Page<Comment> commentsPage = commentRepository.findByPost(post, pageRequest);
        return CommentsDto.from(commentsPage.getContent(), commentsPage.getTotalElements());
    }

    @Transactional
    public void likePost(Long userId, Long postId) {
        // TODO 게시글에 좋아요 수 표시
        likeService.likePost(userId, postId);
    }
}
