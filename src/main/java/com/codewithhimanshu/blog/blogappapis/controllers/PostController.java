package com.codewithhimanshu.blog.blogappapis.controllers;

import com.codewithhimanshu.blog.blogappapis.config.AppConstants;
import com.codewithhimanshu.blog.blogappapis.payloads.PostDto;
import com.codewithhimanshu.blog.blogappapis.payloads.PostResponse;
import com.codewithhimanshu.blog.blogappapis.services.FileService;
import com.codewithhimanshu.blog.blogappapis.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("project.image")
    private String path;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @RequestParam long userId, @RequestParam long catId){
        PostDto addedPost = this.postService.createPost(postDto, userId, catId);
        return new ResponseEntity<>(addedPost, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable long postId, @RequestBody PostDto postDto){
        PostDto dto = this.postService.updatePost(postId, postDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
                                                    @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                    @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR, required = false) String sortDir){
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize, sortBy, sortDir);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long postId){
        PostDto postDto = this.postService.getPostById(postId);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("/ByUser")
    public ResponseEntity<List<PostDto>> getPostByUser(@RequestParam long userId){
        List<PostDto> postDtos = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/ByCategory")
    public ResponseEntity<List<PostDto>> getPostByCategory(@RequestParam long catId){
        List<PostDto> postDtos = this.postService.getPostByCategory(catId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> getPostByTitleKeyword(@RequestParam String keyword){
        List<PostDto> postDtos = this.postService.postsContainingTitle(keyword);
        return ResponseEntity.ok(postDtos);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable long postId){
        this.postService.deletePost(postId);
    }

//    post image upload
    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImage(@RequestParam("image")MultipartFile image,
                                                     @PathVariable long postId) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);

        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        return  ResponseEntity.ok(this.postService.updatePost(postId, postDto));

    }

    @GetMapping(value = "/image/{imgName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imgName,
                                                 HttpServletResponse response) throws IOException{

        InputStream resource = this.fileService.getResource(path, imgName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());

    }
}
