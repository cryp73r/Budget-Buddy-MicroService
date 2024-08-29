package com.cryp73r.authentication.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DEPOT_USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ROWID_USER")
    private Long userId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Deprecated
//    @Column(name = "ROWID_IDENTIFIER", nullable = false, unique = true)
    @Column(name = "ROWID_IDENTIFIER", insertable = false, updatable = false)
    private String identifier;

    @Column(name = "ENABLED", nullable = false, columnDefinition = "BIT default 1")
    private boolean enabled;

    @CreationTimestamp
    @Column(name = "CREATE_DATE", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "LAST_UPDATE_DATE")
    private Instant updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "DEPOT_USER_ROLE_REL",
            joinColumns = @JoinColumn(name = "ROWID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ROWID_ROLE")
    )
    Set<Role> roles = new HashSet<>();

    public User() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Deprecated
    public String getIdentifier() {
        return identifier;
    }

    @Deprecated
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public Set<Role> getRoles() {return roles;}

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public void removeAllRoles() {
        roles.clear();
    }
}
