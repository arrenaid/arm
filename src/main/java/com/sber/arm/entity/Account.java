package com.sber.arm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int id;
    @Column(name = "number_account")
    private String number;
    @Column(name = "opening_date")
    private LocalDateTime openingDate;
    @Column
    private float balance;
    @Column
    private int owner;

    public Account(String number, LocalDateTime openingDate, float balance, int owner) {
        this.number = number;
        this.openingDate = openingDate;
        this.balance = balance;
        this.owner = owner;
    }

    public Account(String number, LocalDateTime openingDate, int owner) {
        this.number = number;
        this.openingDate =  openingDate;
        this.owner = owner;
    }
}
