package com.fiadomanager.infrastructure.repository;

import com.fiadomanager.models.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByCellphone(String cellphone);

    Client findByName(String name);

    List<Client> findByStatus(Integer status);

}
