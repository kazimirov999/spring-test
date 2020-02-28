package com.example.demo.service;

import com.example.demo.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto saveRole(RoleDto roleDto);

    List<RoleDto> getAllRoles();

    RoleDto getRoleById(Integer id);

    void updateRole(Integer id, RoleDto roleDto);

    void deleteRoleById(Integer id);

    void deleteRoleAll();
}
