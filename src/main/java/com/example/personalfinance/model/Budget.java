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

    @Transient
    private String amountConfig;

    public Budget() {
    }

    public String moneyconfig(int number) {
        String old = "" + number;
        StringBuilder temp = new StringBuilder();
        int count = 0;
        for (int i = old.length() - 1; i >= 0; i--) {
            if (count % 3 == 0 && i != old.length() - 1) {
                temp.append(",").append(old.charAt(i));
            } else {
                temp.append(old.charAt(i));
            }
            count++;
        }
        return reverseString(temp.toString());
    }

    public String reverseString(String temp) {
        StringBuilder a = new StringBuilder();
        for (int i = temp.length() - 1; i >= 0; i--) {
            a.append(temp.charAt(i));
        }
        return a.toString();
    }
}
