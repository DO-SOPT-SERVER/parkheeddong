package com.sopt.demo.repository;

import com.sopt.demo.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<Category, Short> {
}
