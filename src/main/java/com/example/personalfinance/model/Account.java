package com.example.personalfinance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name="user")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="user_Roles",
            joinColumns = @JoinColumn(
                    name="user_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(
                    name ="role_id",referencedColumnName="id")
            )

    private Collection<Account_Roles> Roles;

    public Account() {
    }

}

