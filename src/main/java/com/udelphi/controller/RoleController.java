package com.udelphi.controller;

import java.util.List;
import com.udelphi.dto.RoleDto;
import com.udelphi.service.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a role")
    public RoleDto createRole(
            @ApiParam(value = "Role object store in database table", required = true)
            @RequestBody RoleDto roleDto) {
        return roleService.saveRole(roleDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a role by Id")
    public RoleDto getRole(
            @ApiParam(value = "Role id from which role object will retrieve", required = true)
            @PathVariable int id) {
        return roleService.getRole(id);
    }

    @GetMapping
    @ApiOperation(value = "View a list of available roles", response = List.class)
    public List<RoleDto> getRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a role")
    public void deleteRole(
            @ApiParam(value = "Role Id from which role object will delete from database table", required = true)
            @PathVariable int id) {
        roleService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a role")
    public void updateRole(
            @ApiParam(value = "Role Id to update role object", required = true)
            @PathVariable int id,
            @ApiParam(value = "Update role object", required = true)
            @RequestBody RoleDto roleDto) {
        roleService.updateRole(id, roleDto);
    }


}
