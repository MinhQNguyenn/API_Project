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
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;

    private String name;

    private String detail;
    private Date dataOfLoan;
    private Date deadline;
    private String loaner;
    private int mode;
    @Nullable
    private String note;

    @ManyToOne
    @JoinColumn(name="AccountId")
    private Account account;

    public Loan() {
    }
}
