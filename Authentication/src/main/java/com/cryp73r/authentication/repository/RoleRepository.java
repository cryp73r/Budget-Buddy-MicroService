package com.cryp73r.authentication.repository;

import com.cryp73r.authentication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT rle FROM Role rle WHERE rle.roleName = :roleName")
    Optional<Role> findByRoleName(String roleName);
}
