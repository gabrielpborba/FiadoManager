package com.fiadomanager.infrastructure.repository;

import com.fiadomanager.models.domain.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends MongoRepository<Client, Long> {

    Client findByCellphone(String cellphone);

    Client findByName(String name);

    List<Client> findByStatus(Integer status);

}
