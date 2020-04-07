package com.example.demo.service;

import com.example.demo.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(Integer id);

    void updateUser(Integer id, UserDto userDto);

    void deleteUserById(Integer id);

    void deleteUserAll();
}
