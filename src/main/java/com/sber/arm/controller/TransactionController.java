package com.sber.arm.controller;

import com.sber.arm.ArmApplication;
import com.sber.arm.entity.Account;
import com.sber.arm.entity.Transaction;
import com.sber.arm.repository.AccountRepository;
import com.sber.arm.repository.TransactionRepository;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class TransactionController {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/transaction/{id}")
    public String newTransaction(@PathVariable(value = "id") int accountId, Model model){
        Optional<Account> account = accountRepository.findById(accountId);
        model.addAttribute("title","Превод");
        model.addAttribute("account",account.get());
        return "transaction";
    }
    @PostMapping("/transaction/{id}")
        public String newTransaction(@PathVariable(value = "id") int accountId, @RequestParam String debit,
                                     @RequestParam String credit, @RequestParam float amount, Model model){
        Optional<Account> accountCredit = accountRepository.findByNumber(credit);
        Optional<Account> accountDebit = accountRepository.findById(accountId);
        if(transaction(accountDebit.get(),accountCredit.get(),amount))
            return "redirect:/success/"+accountId;
        else
            return "redirect:/error/"+accountId;
    }
    @GetMapping("/error/{id}")
    public String transactionError(@PathVariable(value = "id") int accountId,Model model){
        model.addAttribute("title","ошибка перевода");
        model.addAttribute("ref","/account/" + accountId);
        model.addAttribute("title_ref","вернуться к счету");
        return "info";
    }
    @GetMapping("/success/{id}")
    public String transactionSuccess(@PathVariable(value = "id") int accountId,Model model){
        model.addAttribute("title","Перевод успешно выполнен");
        model.addAttribute("ref","/account/" + accountId);
        model.addAttribute("title_ref","вернуться к счету");
        return "info";
    }

    private boolean transaction(Account debit, Account credit, float amount){
        try{
            debit.setBalance( debit.getBalance() - amount);
            credit.setBalance( credit.getBalance() + amount);
            Transaction transaction = new Transaction(amount, debit.getNumber(), credit.getNumber());
            accountRepository.save(debit);
            accountRepository.save(credit);
            transactionRepository.save(transaction);
            ArmApplication.logger.log(Level.INFO, "new transaction: " + transaction);
            return true;
        }catch (Exception e){
            ArmApplication.logger.error(e);
            return false;
        }
    }

}
