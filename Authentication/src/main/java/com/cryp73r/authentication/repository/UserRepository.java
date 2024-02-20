package com.cryp73r.authentication.repository;

import com.cryp73r.authentication.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
