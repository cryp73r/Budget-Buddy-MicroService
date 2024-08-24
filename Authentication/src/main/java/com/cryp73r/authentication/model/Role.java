package com.cryp73r.authentication.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "DEPOT_ROLE")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "ROWID_ROLE")
    private Long roleId;

    @Column(name = "ROLE_NAME", unique = true, nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;

    public Role() {}

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
