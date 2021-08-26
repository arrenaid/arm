package com.sber.arm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transact")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int id;
    @Column
    private float amount;
    @Column(name = "debit_account")
    private int [] debitAccount;
    @Column(name = "credit_account")
    private int [] creditAccount;
    @Column(name = "transaction_time")
    private Date transactionTime;
}
