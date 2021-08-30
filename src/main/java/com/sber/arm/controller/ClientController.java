package com.sber.arm.controller;

import com.sber.arm.ArmApplication;
import com.sber.arm.entity.Account;
import com.sber.arm.entity.Client;
import com.sber.arm.repository.AccountRepository;
import com.sber.arm.repository.ClientRepository;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ClientController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/")
    public String home(Model model){
        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients",clients);
        model.addAttribute("title","Список всех клиентов:");
        return "home";
    }
    @GetMapping("/client/{id}")
    public String client(@PathVariable(value = "id") int clientId, Model model){
        Optional<Client> client = clientRepository.findById(clientId);
        List<Account> accounts = accountRepository.findByOwner(clientId);
        model.addAttribute("client", client.get());
        model.addAttribute("title","Клиент");
        model.addAttribute("accounts",accounts);
        return "client";
    }
    @GetMapping("/client/add")
    public String clientAdd(Model model){
        model.addAttribute("title","Новый клиент");
        return "client-add";
    }
    @PostMapping("/client/add")
    public String clientAdd(@RequestParam String last_name, @RequestParam String first_name, @RequestParam String middle_name,
                            @RequestParam("date_of_birth") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_of_birth,
                            Model model){
        Client client = new Client(last_name,first_name, middle_name, date_of_birth );
        clientRepository.save(client);
        ArmApplication.logger.log(Level.INFO, " new client: " + client);
        return "redirect:/";
    }
    @RequestMapping("/search")
    public String search(@RequestParam(value = "key", required = false) String key,Model model){
        String[] splitKey = key.split(" ");
        Set<Client> set = new HashSet<>();
        for(String split: splitKey){
            set.addAll(clientRepository.findByLastNameContainingIgnoreCase(split));
            set.addAll(clientRepository.findByFirstNameContainingIgnoreCase(key));
            set.addAll(clientRepository.findByMiddleNameContainingIgnoreCase(key));
        }
        List<Client> clients = new LinkedList<>(set);
        model.addAttribute("clients", clients);
        model.addAttribute("title","Результаты поиска клиентов:");
        return "home";
    }


}
