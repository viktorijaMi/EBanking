package com.ebanking.ebanking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PrimaryAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private int number;

    private Double accountBalance;

    @OneToMany(mappedBy = "fromAccount")
    private List<PrimaryTransaction> primaryTransactionList;

    public PrimaryAccount(String username, int number, Double accountBalance) {
        this.username = username;
        this.number = number;
        this.accountBalance = accountBalance;
        this.primaryTransactionList = new ArrayList<>();
    }
}
