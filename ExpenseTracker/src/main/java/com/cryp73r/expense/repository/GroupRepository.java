package com.cryp73r.expense.repository;

import com.cryp73r.expense.model.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, String> {
}
