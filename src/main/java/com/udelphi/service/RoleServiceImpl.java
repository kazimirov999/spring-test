package com.udelphi.service;

import java.util.List;
import com.udelphi.dto.RoleDto;
import com.udelphi.exception.EntityNotFoundException;
import com.udelphi.model.Role;
import com.udelphi.repository.RoleRepository;
import static java.util.stream.Collectors.toList;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public RoleDto saveRole(RoleDto roleDto) {
        Role saveRole = roleRepository.save(modelMapper.map(roleDto, Role.class));
        return modelMapper.map(saveRole, RoleDto.class);
    }

    public RoleDto getRole(int id) {
        return roleRepository.findById(id)
                .map(role -> modelMapper.map(role, RoleDto.class))
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(toList());
    }

    public void deleteById(int id) {
        roleRepository.deleteById(id);
    }

    public void updateRole(int id, RoleDto roleDto) {
        roleRepository.findById(id)
                .map(role -> modelMapper.map(roleDto, Role.class))
                .ifPresentOrElse(roleRepository::save,
                        () -> {
                            throw new EntityNotFoundException("Entity not found with id: " + id);
                        });
    }

}
