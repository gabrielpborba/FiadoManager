package com.fiadomanager.infrastructure.repository;

import com.fiadomanager.models.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

}
