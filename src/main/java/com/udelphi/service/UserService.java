package com.udelphi.service;

import java.util.List;
import com.udelphi.dto.OrderDto;
import com.udelphi.dto.ProductDto;
import com.udelphi.dto.RoleDto;
import com.udelphi.dto.UserDto;
import com.udelphi.model.User;

public interface UserService {
    UserDto saveUser(UserDto userDto);

    UserDto getUser(int id);

    List<UserDto> getAllUsers();

    void deleteById(int id);

    void updateUser(int id, UserDto userDto);

    List<ProductDto> getAllProductsByUserId(int id);

    List<RoleDto> getAllRolesByUserId(int id);

    List<OrderDto> getAllOrdersByUserId(int id);

    User getUserByEmail(String email);

    void deleteUserRole(int id, int roleId);
}
