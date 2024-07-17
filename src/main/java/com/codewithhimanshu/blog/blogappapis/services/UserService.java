package com.codewithhimanshu.blog.blogappapis.services;

import com.codewithhimanshu.blog.blogappapis.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(long userId,UserDto userDto);
    UserDto getUserById(long userId);
    List<UserDto> getAllUsers();
    void deleteUser(long userId);
}
