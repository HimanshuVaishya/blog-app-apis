package com.codewithhimanshu.blog.blogappapis.repositories;

import com.codewithhimanshu.blog.blogappapis.entity.Category;
import com.codewithhimanshu.blog.blogappapis.entity.Post;
import com.codewithhimanshu.blog.blogappapis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContainingIgnoreCase(String keyword);
}
