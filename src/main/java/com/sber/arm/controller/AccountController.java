package com.sber.arm.controller;

import com.sber.arm.entity.Account;
import com.sber.arm.entity.Client;
import com.sber.arm.entity.Transaction;
import com.sber.arm.repository.AccountRepository;
import com.sber.arm.repository.ClientRepository;
import com.sber.arm.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/account/add/{id}")
    public String accountAdd(@PathVariable(value = "id") int clientId, Model model){
        Account account = new Account(new Generator().generateNumber(), LocalDateTime.now(), clientId);
        model.addAttribute("title","Новый cчет");
        model.addAttribute("account",account);
        return "account-add";
    }
    //@DateTimeFormat(pattern="yyyy-MM-dd")
    @PostMapping("/account/add/{id}")
    public String accountAdd(@PathVariable(value = "id") int clientId, @RequestParam String number,
                             @RequestParam float balance, @RequestParam int owner, Model model){
        Account account = new Account(number, LocalDateTime.now(), balance, owner);
        accountRepository.save(account);
        return "redirect:/client/" + clientId;
    }
    @GetMapping("/account/{id}")
    public String accountView(@PathVariable(value = "id") int accountId, Model model){
        Optional<Account> account = accountRepository.findById(accountId);
        List<Transaction> credit = transactionRepository.findAllByCreditAccount(account.get().getNumber());
        List<Transaction> debit = transactionRepository.findAllByDebitAccount(account.get().getNumber());
        Optional<Client> optionalClient = clientRepository.findById(account.get().getOwner());
        model.addAttribute("title","счет");
        model.addAttribute("account",account.get());
        model.addAttribute("owner", optionalClient.get());
        model.addAttribute("credit",credit);
        model.addAttribute("debit",debit);
        return "account";
    }
}
