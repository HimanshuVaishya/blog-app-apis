package com.codewithhimanshu.blog.blogappapis.services;

import com.codewithhimanshu.blog.blogappapis.payloads.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long userId, long postId);
    CommentDto getComment(long commentId);
    void deleteComment(long commentId);

    List<CommentDto> getCommentByPost(long postId);
}
