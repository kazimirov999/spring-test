package com.example.demo.service;

import com.example.demo.dto.RoleDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public RoleDto saveRole(RoleDto roleDto) {
        Role persist = roleRepository.save(modelMapper.map(roleDto, Role.class));
        return modelMapper.map(persist, RoleDto.class);
    }

    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(toList());
    }

    public RoleDto getRoleById(Integer id) {
        return roleRepository.findById(id)
                .map(role -> modelMapper.map(role, RoleDto.class))
                .orElseThrow(()-> new EntityNotFoundException("Role not found"));
    }

    public void updateRole(Integer id, RoleDto roleDto) {
        roleRepository.findById(id)
                .map(role -> modelMapper.map(roleDto, Role.class))
                .ifPresentOrElse(roleRepository::save,
                        () -> {
                            throw new EntityNotFoundException("Role not found by id " + id);
                        });
    }

    public void deleteRoleById(Integer id) {
        roleRepository.deleteById(id);
    }

    public void deleteRoleAll() {
        roleRepository.deleteAll();
    }

}
