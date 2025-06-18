package com.example.trainingbygpt.projection;

public interface PostProjection {
    Long getPostId();
    String getTitle();
    String getContent();
    Integer getCommentCount();
}
