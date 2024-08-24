package com.cryp73r.authentication.service;

import com.cryp73r.authentication.dto.RoleDTO;
import com.cryp73r.authentication.mapper.RoleMapper;
import com.cryp73r.authentication.model.Role;
import com.cryp73r.authentication.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    final
    RoleRepository roleRepository;

    final
    RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public RoleDTO addRole(RoleDTO roleDTO) {
        Role savedRole = roleRepository.save(roleMapper.RoleDTOToRole(roleDTO));
        return roleMapper.RoleToRoleDTO(savedRole);
    }

    public RoleDTO getRole(String roleName) {
        return roleMapper.RoleToRoleDTO(roleRepository.findByRoleName(roleName).orElseThrow(() -> new RuntimeException("Role not found")));
    }

    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::RoleToRoleDTO)
                .collect(Collectors.toList());
    }
}
