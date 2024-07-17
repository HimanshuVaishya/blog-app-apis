package com.codewithhimanshu.blog.blogappapis.payloads;

import com.codewithhimanshu.blog.blogappapis.entity.Category;
import com.codewithhimanshu.blog.blogappapis.entity.Comment;
import com.codewithhimanshu.blog.blogappapis.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.annotation.Repeatable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private long postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;

    private UserDto user;
    private CategoryDto category;
    private Set<CommentDto> comments = new TreeSet<>();

}
