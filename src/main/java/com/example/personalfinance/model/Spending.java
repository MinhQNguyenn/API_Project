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

    @Transient
    private String amountConfig;

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
        this.amountConfig = moneyconfig(amount);
    }

    @Override
    public String toString() {
        return "Spending{" +
                "id=" + id +
                ", amount=" + amount +
                ", name='" + name + '\'' +
                ", dateOfSpending=" + dateOfSpending +
                ", detail='" + detail + '\'' +
                ", mode=" + mode +
                ", note='" + note + "\'}";
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

    public boolean equals(Spending s) {
        boolean check = true;
        if(!this.note.isEmpty() && !s.note.isEmpty() && this.note.compareTo(s.note) == 0) check=false;
        if(this.amount == s.amount) check=false;
        if(!(this.dateOfSpending == null) && !(s.dateOfSpending == null) && this.dateOfSpending.compareTo(s.dateOfSpending)==0) check=false;
        if(!this.name.isEmpty() && !s.name.isEmpty() && this.name.compareTo(s.name)==0) check =false;
        if(!this.detail.isEmpty() && !s.detail.isEmpty() && this.detail.compareTo(s.detail)==0) check=false;
        return check;
    }
}

