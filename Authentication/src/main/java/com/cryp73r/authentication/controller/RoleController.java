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
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'SUPER_USER')")
    @PostMapping(value = "/role", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addRole(@RequestBody RoleDTO roleDTO) {
        RoleDTO savedRole = roleService.addRole(roleDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{roleName}")
                .buildAndExpand(savedRole.getRoleName()).toUri();
        return ResponseEntity.created(location).body(null);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'SUPER_USER')")
    @GetMapping(value = {"/role", "/role/{roleName}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoleDTO>> getRole(@PathVariable(required = false) Optional<String> roleName) {
        if (roleName.isPresent()) return ResponseEntity.ok(List.of(roleService.getRole(roleName.toString())));
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
