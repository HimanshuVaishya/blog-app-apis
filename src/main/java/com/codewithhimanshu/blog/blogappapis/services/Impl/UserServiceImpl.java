package com.codewithhimanshu.blog.blogappapis.services.Impl;

import com.codewithhimanshu.blog.blogappapis.entity.User;
import com.codewithhimanshu.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.codewithhimanshu.blog.blogappapis.payloads.UserDto;
import com.codewithhimanshu.blog.blogappapis.repositories.UserRepository;
import com.codewithhimanshu.blog.blogappapis.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(long userId, UserDto userDto) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User"," Id ", userId));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User updatedUser = this.userRepository.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," Id ",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," Id ", userId));
        this.userRepository.delete(user);
    }

    public User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);

//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setAbout(userDto.getAbout());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
