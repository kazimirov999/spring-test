package com.example.demo.controllers;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation(value = "Get all users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user by id")
    public UserDto getUserById(
            @ApiParam(value = "The user ID that will return the user from the database table")
            @PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a user")
    public UserDto addUser(
            @ApiParam(value = "User object store in database table", required = true)
            @RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete user by id")
    public void deleteUserDyId(
            @ApiParam(value = "User ID to delete the user from the database table")
            @PathVariable Integer id) {
        userService.deleteUserById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete all users")
    public void deleteUserAll() {
        userService.deleteUserAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update user by id")
    public void updateUserById(
            @ApiParam(value = "User ID to be updated in the database table")
            @PathVariable Integer id,
            @ApiParam(value = "Update user object")
            @RequestBody UserDto userDto) {
        userService.updateUser(id, userDto);
    }
}
