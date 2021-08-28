package com.sber.arm.repository;

import com.sber.arm.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findAllByCreditAccount(String creditAccount);
    List<Transaction> findAllByDebitAccount(String debitAccount);
}
