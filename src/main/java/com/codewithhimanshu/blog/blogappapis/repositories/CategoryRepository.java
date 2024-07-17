package com.codewithhimanshu.blog.blogappapis.repositories;

import com.codewithhimanshu.blog.blogappapis.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
