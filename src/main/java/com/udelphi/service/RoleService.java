package com.udelphi.service;

import java.util.List;
import com.udelphi.dto.RoleDto;

public interface RoleService {
    RoleDto saveRole(RoleDto roleDto);

    RoleDto getRole(int id);

    List<RoleDto> getAllRoles();

    void deleteById(int id);

    void updateRole(int id, RoleDto roleDto);

}
