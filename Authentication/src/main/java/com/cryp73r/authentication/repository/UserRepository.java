package com.cryp73r.authentication.repository;

import com.cryp73r.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT usr FROM User usr WHERE usr.identifier = :identifier")
    Optional<User> findByIdentifier(String identifier);
}
