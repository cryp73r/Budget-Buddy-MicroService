package com.cryp73r.authentication.mapper;

import com.cryp73r.authentication.dto.RoleDTO;
import com.cryp73r.authentication.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleDTO RoleToRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(role.getRoleId());
        roleDTO.setRoleName(role.getRoleName());
        return roleDTO;
    }

    public Role RoleDTOToRole(RoleDTO roleDTO) {
        Role role = new Role();
        if (roleDTO.getRoleId() != null) role.setRoleId(roleDTO.getRoleId());
        role.setRoleName(roleDTO.getRoleName());
        return role;
    }
}
