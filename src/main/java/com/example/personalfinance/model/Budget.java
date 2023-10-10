package com.example.personalfinance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;
    private int month;
    private int year;
    @ManyToOne
    @JoinColumn(name = "AccountId")
    private Account AccountId;

    @ManyToOne
    @JoinColumn(name = "Saving")
    private Saving saving;

    public Budget() {
    }
}
