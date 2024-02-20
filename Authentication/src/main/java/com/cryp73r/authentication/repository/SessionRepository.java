package com.cryp73r.authentication.repository;

import com.cryp73r.authentication.model.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Long> {
}
