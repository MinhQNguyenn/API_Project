package com.example.personalfinance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Saving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;
    private String detail;
    @ManyToOne
    @JoinColumn(name = "AccountId")
    private Account AccountId;

    public Saving() {

    }
}
