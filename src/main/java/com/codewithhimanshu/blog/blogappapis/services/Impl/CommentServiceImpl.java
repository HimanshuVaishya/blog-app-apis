package com.codewithhimanshu.blog.blogappapis.services.Impl;

import com.codewithhimanshu.blog.blogappapis.entity.Comment;
import com.codewithhimanshu.blog.blogappapis.entity.Post;
import com.codewithhimanshu.blog.blogappapis.entity.User;
import com.codewithhimanshu.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.codewithhimanshu.blog.blogappapis.payloads.CommentDto;
import com.codewithhimanshu.blog.blogappapis.repositories.CommentRepository;
import com.codewithhimanshu.blog.blogappapis.repositories.PostRepository;
import com.codewithhimanshu.blog.blogappapis.repositories.UserRepository;
import com.codewithhimanshu.blog.blogappapis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, long userId, long postId) {
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        return this.modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public CommentDto getComment(long commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Commnet", "id", commentId));
        return this.modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public void deleteComment(long commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Commnet", "id", commentId));
        this.commentRepository.delete(comment);
    }

    @Override
    public List<CommentDto> getCommentByPost(long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id", postId));
        List<Comment> comments = this.commentRepository.findAllByPost(post);

        List<CommentDto> commentDtos = comments.stream().map(comment -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
        return commentDtos;
    }
}
