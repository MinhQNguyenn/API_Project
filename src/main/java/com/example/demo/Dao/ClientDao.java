package com.example.demo.Dao;

import com.example.demo.Model.Client;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientDao<T> {

    Iterable<T> findAll();

    Optional<T> findById(int id);

    void remove(int id);
}
