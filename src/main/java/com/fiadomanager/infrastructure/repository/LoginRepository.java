package com.fiadomanager.infrastructure.repository;

import com.fiadomanager.models.domain.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends MongoRepository<Users, Long> {

    Users findByUsername(String username);

}
