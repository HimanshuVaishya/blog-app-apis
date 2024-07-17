package com.codewithhimanshu.blog.blogappapis.services;

import com.codewithhimanshu.blog.blogappapis.payloads.PostDto;
import com.codewithhimanshu.blog.blogappapis.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, long userId, long categoryId);
    PostDto updatePost(long postId, PostDto postDto);
    void deletePost(long postId);
    PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(long postId);

    List<PostDto> getPostByCategory(long catId);
    List<PostDto> getPostByUser(long userId);
//    search post by keyword
    List<PostDto> postsContainingTitle(String keyword);
}
