package com.codewithhimanshu.blog.blogappapis.repositories;

import com.codewithhimanshu.blog.blogappapis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
