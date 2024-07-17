package com.codewithhimanshu.blog.blogappapis.services.Impl;

import com.codewithhimanshu.blog.blogappapis.entity.Category;
import com.codewithhimanshu.blog.blogappapis.entity.Post;
import com.codewithhimanshu.blog.blogappapis.entity.User;
import com.codewithhimanshu.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.codewithhimanshu.blog.blogappapis.payloads.PostDto;
import com.codewithhimanshu.blog.blogappapis.payloads.PostResponse;
import com.codewithhimanshu.blog.blogappapis.repositories.CategoryRepository;
import com.codewithhimanshu.blog.blogappapis.repositories.PostRepository;
import com.codewithhimanshu.blog.blogappapis.repositories.UserRepository;
import com.codewithhimanshu.blog.blogappapis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public PostDto createPost(PostDto postDto, long userId, long categoryId) {

        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user id ", userId));
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id ", userId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post addedPost = this.postRepository.save(post);

        return this.modelMapper.map(addedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        this.postRepository.save(post);
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public void deletePost(long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
        this.postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc"))
            sort = Sort.by(sortBy).ascending();
        else
            sort = Sort.by(sortBy).descending();
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePosts = this.postRepository.findAll(p);
        List<Post> posts = pagePosts.getContent();
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElement(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(long catId) {
        Category category = this.categoryRepository.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category", "category id", catId));
        List<Post> posts = this.postRepository.findByCategory(category);
        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByUser(long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "user id", userId));
        List<Post> posts = this.postRepository.findByUser(user);
        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> postsContainingTitle(String keyword) {
        List<Post> posts = this.postRepository.findByTitleContainingIgnoreCase(keyword);
        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }
}
