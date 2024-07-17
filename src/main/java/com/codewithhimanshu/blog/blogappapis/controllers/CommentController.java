package com.codewithhimanshu.blog.blogappapis.controllers;

import com.codewithhimanshu.blog.blogappapis.payloads.ApiResponse;
import com.codewithhimanshu.blog.blogappapis.payloads.CommentDto;
import com.codewithhimanshu.blog.blogappapis.payloads.PostDto;
import com.codewithhimanshu.blog.blogappapis.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @RequestParam long userId, @RequestParam long postId){
        CommentDto comment = this.commentService.createComment(commentDto, userId, postId);
        return new  ResponseEntity(comment, HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto>  getComment(@PathVariable long commentId){
        CommentDto commentDto = this.commentService.getComment(commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getCommentByPost(@RequestParam long postId){
        List<CommentDto> commentDtos = this.commentService.getCommentByPost(postId);
        return new ResponseEntity<>(commentDtos, HttpStatus.FOUND);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable long commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("comment deleted", true), HttpStatus.OK);
    }

}
