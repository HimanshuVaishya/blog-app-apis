package com.codewithhimanshu.blog.blogappapis.payloads;

import com.codewithhimanshu.blog.blogappapis.entity.Post;
import com.codewithhimanshu.blog.blogappapis.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private long id;
    private String content;
}
