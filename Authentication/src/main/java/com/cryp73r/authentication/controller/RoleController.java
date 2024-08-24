package com.cryp73r.authentication.controller;

import com.cryp73r.authentication.dto.RoleDTO;
import com.cryp73r.authentication.service.RoleService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'SUPER_USER')")
    @PostMapping(value = "/role", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addRole(RoleDTO roleDTO) {
        RoleDTO savedRole = roleService.addRole(roleDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{roleId}")
                .buildAndExpand(savedRole.getRoleId()).toUri();
        return ResponseEntity.created(location).body(null);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'SUPER_USER')")
    @GetMapping(value = "/role/{roleName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoleDTO>> getRole(@PathVariable(required = false) String roleName) {
        if (roleName != null) return ResponseEntity.ok(List.of(roleService.getRole(roleName)));
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
