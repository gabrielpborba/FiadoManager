package com.fiadomanager.infrastructure.repository;

import com.fiadomanager.models.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByCellphone(String cellphone);

}
