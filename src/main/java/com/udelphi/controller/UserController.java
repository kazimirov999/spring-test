package com.udelphi.controller;

import java.util.List;
import com.udelphi.dto.OrderDto;
import com.udelphi.dto.ProductDto;
import com.udelphi.dto.RoleDto;
import com.udelphi.dto.UserDto;
import com.udelphi.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a user")
    public UserDto createUser(
            @ApiParam(value = "User object store in database table", required = true)
            @RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a user by Id")
    public UserDto getUser(
            @ApiParam(value = "User id from which user object will retrieve", required = true)
            @PathVariable int id) {
        return userService.getUser(id);
    }

    @GetMapping
    @ApiOperation(value = "View a list of available users", response = List.class)
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a user")
    public void deleteUser(
            @ApiParam(value = "User Id from which user object will delete from database table", required = true)
            @PathVariable int id) {
        userService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a user")
    public void updateUser(
            @ApiParam(value = "User Id to update user object", required = true)
            @PathVariable int id,
            @ApiParam(value = "Update user object", required = true)
            @RequestBody UserDto userDto) {
        userService.updateUser(id, userDto);
    }

    @GetMapping("/{id}/products")
    @ApiOperation(value = "View a list of available user product", response = List.class)
    public List<ProductDto> getAllProductsFromUser
            (@ApiParam(value = "User Id from which products objects will retrieve", required = true)
             @PathVariable int id) {
        return userService.getAllProductsByUserId(id);
    }

    @GetMapping("/{id}/roles")
    @ApiOperation(value = "View a list of available user role", response = List.class)
    public List<RoleDto> getAllRolesFromUser(
            @ApiParam(value = "User Id from which roles objects will retrieve", required = true)
            @PathVariable int id) {
        return userService.getAllRolesByUserId(id);
    }

    @DeleteMapping("/{id}/roles/{roleId}")
    @ApiOperation(value = "Delete role from user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoleFromUser(
            @ApiParam(value = "User Id from which roles objects will delete", required = true)
            @PathVariable int id,
            @ApiParam(value = "Role Id from which roles objects will delete", required = true)
            @PathVariable int roleId) {

        userService.deleteUserRole(id, roleId);
    }

    @GetMapping("/{id}/orders")
    @ApiOperation(value = "View a list of available user order", response = List.class)
    public List<OrderDto> getAllOrdersFromUser(
            @ApiParam(value = "User Id from which orders objects will retrieve", required = true)
            @PathVariable int id) {
        return userService.getAllOrdersByUserId(id);
    }
}