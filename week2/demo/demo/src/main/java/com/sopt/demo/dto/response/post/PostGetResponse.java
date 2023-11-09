package com.sopt.demo.dto.response.post;

import com.sopt.demo.domain.Post;

public record PostGetResponse(Long id, String title, String Content) {
    public static PostGetResponse of(Post post) {
        return new PostGetResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent()
                );
    }
}
