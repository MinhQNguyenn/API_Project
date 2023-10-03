package com.example.personalfinance.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Spending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;
    private String name;
    private Date dateOfSpending;
    private String detail;
    @ManyToOne
    @JoinColumn(name = "AccountId")
    private Account AccountId;

    public Spending() {

    }
}
