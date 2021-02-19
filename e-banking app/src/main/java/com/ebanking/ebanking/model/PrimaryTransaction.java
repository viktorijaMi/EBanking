package com.ebanking.ebanking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PrimaryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private String description;
    private Double amount;

    @OneToOne
    private PrimaryAccount toAccount;

    @ManyToOne
    private PrimaryAccount fromAccount;

    public PrimaryTransaction(String description, Double amount, PrimaryAccount fromAccount, PrimaryAccount toAccount) {
        this.date = LocalDateTime.now();
        this.description = description;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }
}
