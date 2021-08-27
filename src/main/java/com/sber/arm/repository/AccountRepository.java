package com.sber.arm.repository;

import com.sber.arm.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    public List<Account> findByOwner(int owner);
}
