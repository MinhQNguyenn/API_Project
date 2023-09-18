package com.example.demo.Service;

import com.example.demo.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
//    @Procedure(name = "Client.getClient")
//    List<Client> getClient();

    @Procedure(value = "FIND_CLIENT")
    public List<Client> fetchGetClientProcedure();
}
