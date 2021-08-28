package com.sber.arm.repository;

import com.sber.arm.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    public List<Account> findByOwner(int owner);

    Optional<Account> findByNumber(String number);
}
