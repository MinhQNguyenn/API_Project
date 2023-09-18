package com.example.demo.Dao;

import com.example.demo.Model.Client;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientDataAccessService implements ClientDao <Client>{
    private ClientDao clientDao;

    @Override
    public Iterable<Client> findAll() {
        return null;
    }

    @Override
    public Optional<Client> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void remove(int id) {

    }
}
