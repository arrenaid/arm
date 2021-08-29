package com.sber.arm.repository;

import com.sber.arm.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    List<Client> findByLastNameContainingIgnoreCase(String lastName);
    List<Client> findByFirstNameContainingIgnoreCase(String firstName);
    List<Client> findByMiddleNameContainingIgnoreCase(String middleName);
}
