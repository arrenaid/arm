package com.sber.arm.controller;

import com.sber.arm.ArmApplication;
import com.sber.arm.entity.Client;
import com.sber.arm.entity.Role;
import com.sber.arm.repository.ClientRepository;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/")
    public String clientMain(Model model){
        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients",clients);
        model.addAttribute("title","Список всех клиентов:");
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
                            @RequestParam String status, Model model){
        Client client = new Client(last_name,first_name, middle_name, date_of_birth, Role.Client.equals(status)? Role.Client: Role.Employee );
        clientRepository.save(client);
        ArmApplication.logger.log(Level.INFO, " new client: " + client);
        return "redirect:/";
    }

}
