package com.sber.arm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private String debitAccount;
    @Column(name = "credit_account")
    private String creditAccount;
    @Column(name = "transaction_time")
    private LocalDateTime transactionTime;

    public Transaction(float amount, String debitAccount, String creditAccount) {
        this.amount = amount;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.transactionTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", debitAccount='" + debitAccount + '\'' +
                ", creditAccount='" + creditAccount + '\'' +
                ", transactionTime=" + transactionTime +
                '}';
    }
}
