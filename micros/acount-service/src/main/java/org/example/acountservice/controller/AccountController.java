package org.example.acountservice.controller;

import org.example.acountservice.entity.Account;
import org.example.acountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/debit")
    public String debitAccount(@RequestParam Long clientId, @RequestParam double amount) {
        boolean success = accountService.debitAccountByClientId(clientId, amount);
        if (success) {
            return "Account debited successfully";
        } else {
            return "Insufficient balance or account not found";
        }
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}