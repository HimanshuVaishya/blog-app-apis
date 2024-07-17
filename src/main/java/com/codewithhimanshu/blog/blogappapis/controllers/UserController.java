package com.codewithhimanshu.blog.blogappapis.controllers;

import com.codewithhimanshu.blog.blogappapis.payloads.ApiResponse;
import com.codewithhimanshu.blog.blogappapis.payloads.UserDto;
import com.codewithhimanshu.blog.blogappapis.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

//    create User
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

//    get suer by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long userId){
        UserDto userDto = this.userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.FOUND);
    }

//    get all user
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos = this.userService.getAllUsers();
        return ResponseEntity.ok(userDtos);
    }

//  update user by user id
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable long userId, @RequestBody UserDto userDto){
        UserDto updatedUserDto = this.userService.updateUser(userId, userDto);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.FOUND);
    }
//  delete user by id
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable long userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("user deleted successfully", false), HttpStatus.OK);
    }

}
