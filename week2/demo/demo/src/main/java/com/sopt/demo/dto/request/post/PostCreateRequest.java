package com.sopt.demo.dto.request.post;

import com.sopt.demo.domain.category.CategoryId;

public record PostCreateRequest(String title, String content, CategoryId categoryId) {
}
