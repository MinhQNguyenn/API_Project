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

    @Transient
    private String amountConfig;

    @ManyToOne
    @JoinColumn(name="AccountId")
    private Account account;

    public Loan() {
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
