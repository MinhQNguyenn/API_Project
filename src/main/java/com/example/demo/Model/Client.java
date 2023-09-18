package com.example.demo.Model;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.query.Procedure;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NamedStoredProcedureQuery(name="Client.getClient", procedureName = "FIND_CLIENT")
public class Client implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String password;
    private double totalMoney;
    private int totalSpending;
    private int totalSaving;


//    @Procedure(name = "Client.getClient")
//    public List<Client> getClient() {
//        EntityManagerFactory entityManagerFactory = null;
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Query query = entityManager.createNativeQuery("CALL FIND_CLIENT", Client.class); // Create a query by calling the stored procedure
//
//        List<Client> result = query.getResultList(); // Execute the query and obtain the result
//
//        return result;
//    }

    public Client() {
    }

    public Client(Long id, String name, String email, String password, double totalMoney, int totalSpending, int totalSaving) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.totalMoney = totalMoney;
        this.totalSpending = totalSpending;
        this.totalSaving = totalSaving;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setTotalSpending(int totalSpending) {
        this.totalSpending = totalSpending;
    }

    public void setTotalSaving(int totalSaving) {
        this.totalSaving = totalSaving;
    }
}

