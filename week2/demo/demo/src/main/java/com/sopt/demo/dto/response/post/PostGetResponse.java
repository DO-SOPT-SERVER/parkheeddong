package com.sopt.demo.dto.response.post;

import com.sopt.demo.domain.Category;
import com.sopt.demo.domain.Post;

public record PostGetResponse(Long id, String title, String content, String category) {
    public static PostGetResponse of(Post post, Category category) {
        return new PostGetResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                category.getContent()
                );
    }
}
