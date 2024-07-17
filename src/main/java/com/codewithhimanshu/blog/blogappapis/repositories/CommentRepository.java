package com.codewithhimanshu.blog.blogappapis.repositories;

import com.codewithhimanshu.blog.blogappapis.entity.Comment;
import com.codewithhimanshu.blog.blogappapis.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
}
