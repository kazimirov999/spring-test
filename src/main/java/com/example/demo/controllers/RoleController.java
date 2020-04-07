package com.example.demo.controllers;

import com.example.demo.dto.RoleDto;
import com.example.demo.service.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/roles")

public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    @ApiOperation(value = "Get all roles")
    public List<RoleDto> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get roles by id")
    public RoleDto getRoleById(
            @ApiParam(value = "The role ID that will return the role from the database table")
            @PathVariable Integer id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a role")
    public RoleDto addRole(
            @ApiParam(value = "Role object store in database table", required = true)
            @RequestBody RoleDto role) {
        return roleService.saveRole(role);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete role by id")
    public void deleteRoleDyId(
            @ApiParam(value = "Role ID to delete the role from the database table")
            @PathVariable Integer id) {
        roleService.deleteRoleById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete all roles")
    public void deleteRoleAll() {
        roleService.deleteRoleAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update role by id")
    public void updateRoleById(
            @ApiParam(value = "Role ID to be updated in the database table")
            @PathVariable Integer id,
            @ApiParam(value = "Update comment object")
            @RequestBody RoleDto roleDto) {
        roleService.updateRole(id, roleDto);
    }
}
