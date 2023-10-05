package com.example.personalfinance.model;

import com.sun.istack.NotNull;
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
}
