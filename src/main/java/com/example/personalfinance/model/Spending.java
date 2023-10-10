package com.example.personalfinance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

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
    private int mode;
    @Nullable
    private String note;

    @ManyToOne
    @JoinColumn(name = "AccountId")
    private Account AccountId;

    public Spending() {

    }

    public Spending(int id, int amount, String name, Date dateOfSpending, String detail, int mode, @Nullable String note) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.dateOfSpending = dateOfSpending;
        this.detail = detail;
        this.mode = mode;
        this.note = note;
    }
}
