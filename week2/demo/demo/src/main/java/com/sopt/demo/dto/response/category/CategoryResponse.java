package com.sopt.demo.dto.response.category;

import com.sopt.demo.domain.category.Category;
import lombok.Data;

@Data
public class CategoryResponse {
    private String content;

    public CategoryResponse(String content) {
        this.content = content;
    }

    public static CategoryResponse of(Category category) {
        return new CategoryResponse(category.getContent());
    }
}
